package com.example.contacts.presentation.feature.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contacts.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

   private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initializeAuthViewModel();

        requestLoadingUserId();

        requestLoadingUserPassword();
    }

    /**
     * Construct {@AuthViewModel} class
     */
    private void initializeAuthViewModel() {
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    /**
     * Request user password
     */
    private void requestLoadingUserPassword() {
        authViewModel.loadUserPasswordCredential();
    }

    /**
     * Request user id
     */
    private void requestLoadingUserId() {
        authViewModel.loadUserIdCredential();
    }

}