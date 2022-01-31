package com.example.contacts.domain.usecase;

import com.example.contacts.entity.login.LoginValidation;

public interface FileIOUseCase {
    void saveUserId();

    void saveUserPassword();

    String retrieveUserId();

    String retrieveUserPassword();

    LoginValidation requestLogin(String userId, String userPassword);
}
