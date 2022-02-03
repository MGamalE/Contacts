package com.example.contacts.presentation.feature.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.fileio.FileIOUseCase;
import com.example.contacts.entity.login.LoginValidation;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    @Inject
    FileIOUseCase useCase;

    private MutableLiveData<String> _userId;

    private MutableLiveData<String> _userPassword;

    private MutableLiveData<LoginValidation> _userLoginValidation;

    @Inject
    public LoginViewModel() {
        _userId = new MutableLiveData<>();
        _userPassword = new MutableLiveData<>();
        _userLoginValidation = new MutableLiveData<>();
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

    public void requestLogin(String userId, String userPassword) {
        _userLoginValidation.postValue(useCase.requestLogin(userId, userPassword));
    }

    public LiveData<LoginValidation> isUserCredentialsValid() {
        return _userLoginValidation;
    }
}
