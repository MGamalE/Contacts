package com.example.contacts.domain.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.contacts.domain.core.Constant;
import com.example.contacts.domain.gateway.persistence.MySharedPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Singleton
    @Provides
    public static SharedPreferences provideSharedPreference(@ApplicationContext Context context){
        return context.getSharedPreferences(Constant.PREF_NAME,Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public static MySharedPreference provideMySharedPreference(SharedPreferences sharedPreferences){
        return new MySharedPreference(sharedPreferences);
    }
}
