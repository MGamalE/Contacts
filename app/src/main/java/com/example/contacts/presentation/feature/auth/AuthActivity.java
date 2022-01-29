package com.example.contacts.presentation.feature.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.domain.gateway.persistence.MySharedPreference;
import com.example.contacts.presentation.core.Constant;

import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    @Inject
    MySharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //Load credentials
        readUserCredentials();
    }

    /**
     *  Read from credential file, for secure access
     * @InputStream to access file content
     * @Properties to read the file property keys
     */
    private void readUserCredentials() {
        try {
            if (this.getAssets() != null) {
                InputStream is = this.getAssets().open(Constant.FILE_PATH);
                Properties properties = new Properties();
                properties.load(is);

                saveToSharedPreference(properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert the file credentials to @MySharedPreference
     * @param properties that contains file credentials
     */
    private void saveToSharedPreference(Properties properties) {
        sharedPreference.saveUserId(properties.getProperty(Constant.FILE_USER_ID));
        sharedPreference.saveUserPassword(properties.getProperty(Constant.FILE_USER_PASSWORD));
    }


}