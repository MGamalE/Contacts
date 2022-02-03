package com.example.contacts.domain.repository.fileio;

import com.example.contacts.domain.gateway.persistence.FileIO;
import com.example.contacts.domain.gateway.persistence.SharedPreference;

import java.io.IOException;

import javax.inject.Inject;

/**
 * This repository class has the main role to access
 * the data gateway from {@FileIO} and {@SharedPreference} sources
 */
public class FileIORepositoryImpl implements FileIORepository {

    private FileIO fileIO;

    private SharedPreference sharedPreference;

    @Inject
    public FileIORepositoryImpl(FileIO fileIO, SharedPreference sharedPreference) {
        this.fileIO = fileIO;
        this.sharedPreference = sharedPreference;
    }

    @Override
    public void saveUserIdToPreference() throws IOException {
        sharedPreference.saveUserId(fileIO.loadUserIdToSharedPreference());
    }

    @Override
    public void saveUserPasswordToPreference() throws IOException {
        sharedPreference.saveUserPassword(fileIO.loadUserPasswordToSharedPreference());
    }

    @Override
    public String retrieveUserIdToPreference() {
        return sharedPreference.getUserId();
    }

    @Override
    public String retrieveUserPasswordToPreference() {
        return sharedPreference.getUserPassword();
    }
}
