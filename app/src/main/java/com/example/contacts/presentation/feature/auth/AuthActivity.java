package com.example.contacts.presentation.feature.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.contacts.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
}