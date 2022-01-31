package com.example.contacts.presentation.feature.login;

import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.FileIOUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    @Inject
    FileIOUseCase useCase;

    @Inject
    public LoginViewModel(){

    }

}
