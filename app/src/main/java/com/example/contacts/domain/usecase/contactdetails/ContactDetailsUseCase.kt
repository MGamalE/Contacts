package com.example.contacts.domain.usecase.contactdetails

import com.example.contacts.entity.contactdetails.ContactDetailsResponse
import kotlinx.coroutines.flow.Flow

interface ContactDetailsUseCase {
    suspend fun getContactDetails(contactId: String): Flow<ContactDetailsResponse>
}