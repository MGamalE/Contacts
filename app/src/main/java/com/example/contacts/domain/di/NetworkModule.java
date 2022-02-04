package com.example.contacts.domain.di;

import static com.example.contacts.domain.core.Constant.AUTHORIZATION;
import static com.example.contacts.domain.core.Constant.BASE_URL;

import com.example.contacts.domain.gateway.persistence.SharedPreference;
import com.example.contacts.domain.gateway.remote.ContactAPI;
import com.example.contacts.domain.repository.contactlist.ContactListRepository;
import com.example.contacts.domain.repository.contactlist.ContactListRepositoryImpl;
import com.example.contacts.domain.usecase.contactlist.ContactListUseCase;
import com.example.contacts.domain.usecase.contactlist.ContactListUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class NetworkModule {

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkhttp(HttpLoggingInterceptor httpLoggingInterceptor, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public static HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public static Interceptor providesAuthInterceptor(SharedPreference sharedPreference) {
        return chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader(AUTHORIZATION,
                            Credentials.basic(sharedPreference.getUserId(), sharedPreference.getUserPassword())).build();
            return chain.proceed(request);
        };
    }

    @Singleton
    @Provides
    public static ContactAPI provideContactAPI(Retrofit retrofit) {
        return retrofit.create(ContactAPI.class);
    }

    @Provides
    public static ContactListRepository provideContactListRepository(ContactAPI server) {
        return new ContactListRepositoryImpl(server);
    }

    @Provides
    public static ContactListUseCase provideContactListUseCase(ContactListRepositoryImpl repository) {
        return new ContactListUseCaseImpl(repository);
    }

}
