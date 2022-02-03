package com.example.contacts.domain.repository.fileio;

import java.io.IOException;

public interface FileIORepository {
    void saveUserIdToPreference() throws IOException;

    void saveUserPasswordToPreference() throws IOException;

    String retrieveUserIdToPreference();

    String retrieveUserPasswordToPreference();
}
