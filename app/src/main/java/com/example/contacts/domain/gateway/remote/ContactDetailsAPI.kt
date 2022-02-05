package com.example.contacts.domain.gateway.remote

import com.example.contacts.entity.contactdetails.ContactDetailsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactDetailsAPI {
    @GET("contacts/{contact_id}")
    suspend fun getContactDetails(@Path("contact_id") contactId: String): ContactDetailsResponse
}