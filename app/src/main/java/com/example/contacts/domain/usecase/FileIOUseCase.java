package com.example.contacts.domain.usecase;

import java.io.IOException;

public interface FileIOUseCase {
    void saveUserId();

    void saveUserPassword();

    String retrieveUserId();

    String retrieveUserPassword();
}
