package com.example.contacts.presentation.feature.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.FileIOUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    @Inject
    FileIOUseCase useCase;

    private MutableLiveData<String> _userId;

    private MutableLiveData<String> _userPassword;

    @Inject
    public LoginViewModel() {
        _userId = new MutableLiveData<>();
        _userPassword = new MutableLiveData<>();
    }

    private void retrieveUserId() {
        _userId.postValue(useCase.retrieveUserId());
    }

    public LiveData<String> getUserId() {
        retrieveUserId();
        return _userId;
    }

    private void retrieveUserPassword() {
        _userPassword.postValue(useCase.retrieveUserPassword());
    }

    public LiveData<String> getUserPassword() {
        retrieveUserPassword();
        return _userPassword;
    }

    public void requestLogin(String userId, String userPassword){

    }

}
