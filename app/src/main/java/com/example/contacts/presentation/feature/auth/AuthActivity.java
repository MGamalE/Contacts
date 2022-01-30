package com.example.contacts.presentation.feature.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.domain.gateway.persistence.SharedPreference;
import com.example.contacts.domain.usecase.FileIOUseCase;
import com.example.contacts.presentation.core.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    @Inject
    FileIOUseCase fileIOUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //Load credentials
        saveToSharedPreference();
    }

    /**
     * Insert the file credentials to @MySharedPreference
     */
    private void saveToSharedPreference() {
        try {
            fileIOUseCase.saveUserId();
            fileIOUseCase.saveUserPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}