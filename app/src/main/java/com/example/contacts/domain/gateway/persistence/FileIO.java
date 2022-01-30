package com.example.contacts.domain.gateway.persistence;



import com.example.contacts.domain.core.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileIO {

    private InputStream inputStream;

    private Properties properties;

    public FileIO(InputStream inputStream, Properties properties) {
        this.inputStream = inputStream;
        this.properties = properties;
    }

    public String loadUserIdToSharedPreference() throws IOException {
        properties.load(inputStream);
        return properties.getProperty(Constant.FILE_USER_ID);
    }

    public String loadUserPasswordToSharedPreference() throws IOException {
        properties.load(inputStream);
        return properties.getProperty(Constant.FILE_USER_PASSWORD);
    }
}
