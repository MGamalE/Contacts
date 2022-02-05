package com.example.contacts.domain.repository.contactdetails

import com.example.contacts.entity.contactdetails.ContactDetailsResponse

interface ContactDetailsRepository {
    suspend fun getContactDetails(contactId: String): ContactDetailsResponse
}