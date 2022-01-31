package com.example.contacts.presentation.feature.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.R;
import com.example.contacts.databinding.FragmentLoginBinding;
import com.example.contacts.domain.gateway.persistence.SharedPreference;
import com.example.contacts.domain.usecase.FileIOUseCase;
import com.example.contacts.presentation.feature.auth.AuthViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Inject
    FileIOUseCase useCase;

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

    }

    private void fireLogin() {
        binding.btLogin.setOnClickListener(v -> {

        });
    }

    /**
     * Construct {@LoginViewModel} class
     */
    private void initializeLoginViewModel() {
        loginViewModel=new ViewModelProvider(this).get(LoginViewModel.class);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}