package com.example.contacts.domain.usecase;

import com.example.contacts.domain.repository.FileIORepository;
import com.example.contacts.domain.repository.FileIORepositoryImpl;

import java.io.IOException;

/**
 * This use case class to manipulate
 * the business rules between data sources gateway
 * {@FilIO},{@SharedPreference} and presentation layer {@ViewModel}
 */
public class FileIOUseCaseImpl implements FileIOUseCase{

    private FileIORepository repository;

    public FileIOUseCaseImpl(FileIORepositoryImpl fileIORepository){
        this.repository=fileIORepository;
    }

    @Override
    public void saveUserId() throws IOException {
        repository.saveUserIdToPreference();
    }

    @Override
    public void saveUserPassword() throws IOException {
        repository.saveUserPasswordToPreference();
    }

    @Override
    public String retrieveUserId() {
        return repository.retrieveUserIdToPreference();
    }

    @Override
    public String retrieveUserPassword() {
        return repository.retrieveUserPasswordToPreference();
    }
}
