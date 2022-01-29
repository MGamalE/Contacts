package com.example.contacts.domain.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.contacts.domain.core.Constant;
import com.example.contacts.domain.gateway.persistence.FileIO;
import com.example.contacts.domain.gateway.persistence.SharedPreference;
import com.example.contacts.presentation.core.App;

import java.io.InputStream;
import java.util.Properties;

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
    public static SharedPreferences provideSharedPreference(@ApplicationContext Context context) {
        return context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public static SharedPreference provideMySharedPreference(SharedPreferences sharedPreferences) {
        return new SharedPreference(sharedPreferences);
    }

    @Singleton
    @Provides
    public static Context provideApplicationContext(App myApplication) {
        return myApplication;
    }

    @Singleton
    @Provides
    public static InputStream provideInput(App myApplication) {
        try {
            return myApplication.getAssets().open(com.example.contacts.presentation.core.Constant.FILE_PATH);
        } catch (Exception e) {
            myApplication.getAssets().close();
            e.printStackTrace();
            throw new RuntimeException("File Not Found Exception");
        }
    }

    @Singleton
    @Provides
    public static Properties provideProperties() {
        return new Properties();
    }

    @Singleton
    @Provides
    public static FileIO provideFileIO(InputStream inputStream,Properties properties) {
        return new FileIO(inputStream,properties);
    }
}
