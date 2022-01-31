package com.example.contacts.presentation.feature.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.contacts.R;
import com.example.contacts.databinding.FragmentLoginBinding;
import com.example.contacts.domain.usecase.FileIOUseCase;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fireLogin();

        initializeLoginViewModel();

        initializeUserIdObserver();

        initializeUserPasswordObserver();

        watchIdInputsChanges();

        watchPasswordInputsChanges();

        initializeUserLoginObserver();
    }

    /**
     * Calling request login when click on login button
     */
    private void fireLogin() {
        binding.btLogin.setOnClickListener(v -> {
            if (binding.etUserInput.getText() != null &&
                    binding.etPasswordInput.getText() != null) {
                loginViewModel.requestLogin(binding.etUserInput.getText().toString(),
                        binding.etPasswordInput.getText().toString());
            }
        });
    }

    /**
     * Construct {@LoginViewModel} class
     */
    private void initializeLoginViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    /**
     * Listen to retrieved user id value
     */
    private void initializeUserIdObserver() {
        loginViewModel.getUserId().observe(getViewLifecycleOwner(), userId -> {
            binding.etUserInput.setText(userId);
        });
    }

    /**
     * Listen to retrieved user password value
     */
    private void initializeUserPasswordObserver() {
        loginViewModel.getUserPassword().observe(getViewLifecycleOwner(), userPassword -> {
            binding.etPasswordInput.setText(userPassword);
        });
    }

    /**
     * Observe changes of user password input
     */
    private void watchPasswordInputsChanges() {
        binding.etPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * Hide error label when listen to changes of user password input
                 */
                binding.tvPasswordInputError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Observe changes of user id input
     */
    private void watchIdInputsChanges() {
        binding.etUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * Hide error label when listen to changes of user id input
                 */
                binding.tvUserInputError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Listen to send user credentials login
     */
    private void initializeUserLoginObserver() {
        loginViewModel.isUserCredentialsValid().observe(getViewLifecycleOwner(), loginValidation -> {
            /**
             * If the user credentials data valid, then do login to next screen
             */
            if (loginValidation.isValid()) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_contactsMainActivity2);
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
            /**
             * If the user id credential data is not valid, then do display error
             */
            else if (!loginValidation.isValid() &&
                    !loginValidation.getErrorUserId().isEmpty()) {
                binding.tvUserInputError.setVisibility(View.VISIBLE);
                binding.tvUserInputError.setText(loginValidation.getErrorUserId());
            }
            /**
             * If the user password credential data is not valid, then do display error
             */
            else if ((!loginValidation.isValid() &&
                    !loginValidation.getErrorUserPassword().isEmpty())) {
                binding.tvPasswordInputError.setVisibility(View.VISIBLE);
                binding.tvPasswordInputError.setText(loginValidation.getErrorUserPassword());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}