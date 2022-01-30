package com.example.contacts.domain.usecase;

import java.io.IOException;

public interface FileIOUseCase {
    void saveUserId() throws IOException;
    void saveUserPassword() throws IOException;

    String retrieveUserId();
    String retrieveUserPassword();
}
