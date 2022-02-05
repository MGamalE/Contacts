package com.example.contacts.domain.di.kotlin

import com.example.contacts.domain.gateway.remote.ContactDetailsAPI
import com.example.contacts.domain.repository.contactdetails.ContactDetailsRepository
import com.example.contacts.domain.repository.contactdetails.ContactDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KNetworkModule {

    @Singleton
    @Provides
    fun provideContactDetailsAPI(retrofit: Retrofit): ContactDetailsAPI =
        retrofit.create(ContactDetailsAPI::class.java)

    @Singleton
    @Provides
    fun provideContactDetailsRepository(server: ContactDetailsAPI): ContactDetailsRepository =
        ContactDetailsRepositoryImpl(server)
}