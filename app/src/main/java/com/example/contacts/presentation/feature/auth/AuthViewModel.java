package com.example.contacts.presentation.feature.auth;

import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.fileio.FileIOUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    @Inject
    FileIOUseCase useCase;

    @Inject
    public AuthViewModel() {

    }

    public void loadUserIdCredential() {
        useCase.saveUserId();
    }

    public void loadUserPasswordCredential() {
        useCase.saveUserPassword();
    }
}
