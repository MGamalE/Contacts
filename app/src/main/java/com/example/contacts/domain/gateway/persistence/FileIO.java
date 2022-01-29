package com.example.contacts.domain.gateway.persistence;


import com.example.contacts.presentation.core.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileIO {

    private InputStream inputStream;

    private Properties properties;

    public FileIO(InputStream inputStream,Properties properties){
        this.inputStream=inputStream;
        this.properties=properties;
    }

    public void saveUserIdToSharedPreference(SharedPreference sharedPreference){
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sharedPreference.saveUserId(properties.getProperty(Constant.FILE_USER_ID));
    }

    public void saveUserPasswordToSharedPreference(SharedPreference sharedPreference){
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sharedPreference.saveUserPassword(properties.getProperty(Constant.FILE_USER_PASSWORD));
    }
}
