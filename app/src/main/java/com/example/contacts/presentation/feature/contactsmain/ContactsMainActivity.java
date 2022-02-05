package com.example.contacts.presentation.feature.contactsmain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.contacts.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}