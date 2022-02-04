package com.example.contacts.domain.usecase.fileio;

import com.example.contacts.domain.repository.fileio.FileIORepository;
import com.example.contacts.domain.repository.fileio.FileIORepositoryImpl;
import com.example.contacts.entity.login.LoginValidation;

/**
 * This use case class to manipulate
 * the business rules between data sources gateway
 * {@FilIO},{@SharedPreference} and presentation layer {@ViewModel}
 */
public class FileIOUseCaseImpl implements FileIOUseCase {

    private FileIORepository repository;

    public FileIOUseCaseImpl(FileIORepositoryImpl fileIORepository) {
        this.repository = fileIORepository;
    }

    @Override
    public void saveUserId() {
        try {
            repository.saveUserIdToPreference();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUserPassword() {
        try {
            repository.saveUserPasswordToPreference();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String retrieveUserId() {
        return repository.retrieveUserIdToPreference();
    }

    @Override
    public String retrieveUserPassword() {
        return repository.retrieveUserPasswordToPreference();
    }

    @Override
    public LoginValidation requestLogin(String userId, String userPassword) {
        return validateLoginCredentials(userId, userPassword);
    }

    private LoginValidation validateLoginCredentials(String userId, String userPassword) {
        LoginValidation loginValidation = new LoginValidation();
        if (userId.isEmpty()) {
            loginValidation.setErrorUserId("User id required!");
            loginValidation.setValid(false);
        } else if (userPassword.isEmpty()) {
            loginValidation.setErrorUserPassword("User password required!");
            loginValidation.setValid(false);
        } else {
            repository.saveUserLoginId(userId);
            repository.saveUserLoginPassword(userPassword);
            loginValidation.setValid(true);
        }
        return loginValidation;
    }
}
