package com.example.contacts.domain.repository.contactdetails

import com.example.contacts.domain.gateway.remote.ContactDetailsAPI
import com.example.contacts.entity.contactdetails.ContactDetailsResponse

class ContactDetailsRepositoryImpl(private val server: ContactDetailsAPI) :
    ContactDetailsRepository {

    override suspend fun getContactDetails(contactId: String): ContactDetailsResponse =
        server.getContactDetails(contactId)

}