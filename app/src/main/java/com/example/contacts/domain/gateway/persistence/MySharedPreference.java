package com.example.contacts.domain.gateway.persistence;

import android.content.SharedPreferences;

import com.example.contacts.domain.core.Constant;

public class MySharedPreference {

    private SharedPreferences sharedPreference;


    public MySharedPreference(SharedPreferences mySharedPref) {
        this.sharedPreference = mySharedPref;
    }

    public void saveUserId(String userId) {
        sharedPreference.edit().putString(Constant.USER_ID, userId).apply();
    }

    public String getUserId() {
        return sharedPreference.getString(Constant.USER_ID, "");
    }

    public void saveUserPassword(String userPassword) {
        sharedPreference.edit().putString(Constant.USER_PASSWORD, userPassword).apply();
    }

    public String getUserPassword() {
        return sharedPreference.getString(Constant.USER_PASSWORD, "");
    }
}
