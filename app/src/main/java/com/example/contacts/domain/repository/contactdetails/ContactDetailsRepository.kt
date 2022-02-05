package com.example.contacts.domain.repository.contactdetails

import com.example.contacts.entity.contactdetails.ContactDetailsResponse
import kotlinx.coroutines.flow.Flow

interface ContactDetailsRepository {
    suspend fun getContactDetails(contactId: String): Flow<ContactDetailsResponse>
}