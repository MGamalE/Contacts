package com.example.contacts.presentation.feature.contactlist;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

import static com.example.contacts.presentation.core.Constant.FIRST_PAGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.contacts.R;
import com.example.contacts.databinding.FragmentContactListBinding;
import com.example.contacts.presentation.core.ErrorType;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactListFragment extends Fragment {

    private FragmentContactListBinding binding;

    private ContactListViewModel contactListViewModel;

    private ContactListAdapter contactsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fireSwipeRefreshing();

        initializeContactListViewModel();

        initializeContactListAdapter();

        retrieveContacts(FIRST_PAGE, PAGE_SIZE);

        initializeSuccessRequestObserver();

        initializeLoadingStateObserver();

        initializeErrorStateObserver();

        initializeContactClickedObserver();

    }

    /**
     * Initialize expanded list adapter
     */
    private void initializeContactListAdapter() {
        contactsAdapter = new ContactListAdapter(getActivity(), new ArrayList<>(), new HashMap<>(), contactListViewModel);
        binding.exList.setAdapter(contactsAdapter);
    }

    /**
     * Setup swipe refreshing
     */
    private void fireSwipeRefreshing() {
        binding.SwipeRefreshLayout.setOnRefreshListener(() -> {
            /**
             * Request the first page
             */
            retrieveContacts(FIRST_PAGE, PAGE_SIZE);

            /**
             * Update contacts adapter
             */
            contactsAdapter.releaseData();
        });
    }

    /**
     * Construct {@ContactListViewModel} class
     */
    private void initializeContactListViewModel() {
        contactListViewModel = new ViewModelProvider(this).get(ContactListViewModel.class);
    }

    /**
     * Request list of contacts
     *
     * @param page    The number of requested page
     * @param perPage the count of item request of each page
     */
    private void retrieveContacts(long page, long perPage) {
        contactListViewModel.retrieveContacts(page, perPage);
    }

    /**
     * Listen to retrieved response error
     */
    private void initializeErrorStateObserver() {
        contactListViewModel.stateError().observe(getViewLifecycleOwner(), this::displayErrorMessage);
    }

    /**
     * Listen to retrieved loading state
     */
    private void initializeLoadingStateObserver() {
        contactListViewModel.stateLoading().observe(getViewLifecycleOwner(), loading -> binding.SwipeRefreshLayout.setRefreshing(loading));
    }

    /**
     * Listen to retrieved success data response
     */
    private void initializeSuccessRequestObserver() {
        contactListViewModel.stateSuccess().observe(getViewLifecycleOwner(), response -> contactsAdapter.updateAdapter(response, new ArrayList<>(response.keySet())));
    }

    /**
     * Listen to retrieved clicked contact
     */
    private void initializeContactClickedObserver() {
        contactListViewModel.getContactClicked().observe(getViewLifecycleOwner(), response -> {
           //TODO: Navigate to contact details screen
        });
    }

    /**
     * Assign each error to specified message
     *
     * @param error Enum class hold api request errors
     */
    private void displayErrorMessage(ErrorType error) {
        switch (error) {
            case SERVER_ERROR:
                showMessage(getString(R.string.error_server));
                break;
            case UNAUTHORIZED_ERROR:
                showMessage(getString(R.string.error_un_authorized));
                break;
            case NOT_FOUND_ERROR:
                showMessage(getString(R.string.error_not_found));
                break;
            case FORBIDDEN_ERROR:
                showMessage(getString(R.string.error_permission));
                break;
            case CONNECTION_ERROR:
                showMessage(getString(R.string.error_connection));
                break;
            default:
                showMessage(getString(R.string.error_generic));
                break;
        }
    }

    /**
     * Display snackbar error
     *
     * @param message The error body that will be displayed
     */
    private void showMessage(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}