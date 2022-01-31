package com.example.contacts.presentation.feature.auth;

import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.FileIOUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    @Inject
    FileIOUseCase useCase;

    @Inject
    public AuthViewModel(){

    }

    public void loadUserIdCredential() {
        try {
            useCase.saveUserId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserPasswordCredential() {
        try {
            useCase.saveUserPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
