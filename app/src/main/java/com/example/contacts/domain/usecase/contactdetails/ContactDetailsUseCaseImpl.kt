package com.example.contacts.domain.usecase.contactdetails

import com.example.contacts.domain.repository.contactdetails.ContactDetailsRepository
import com.example.contacts.entity.contactdetails.ContactDetailsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactDetailsUseCaseImpl @Inject constructor(private val repository: ContactDetailsRepository) :
    ContactDetailsUseCase {

    override suspend fun getContactDetails(contactId: String): Flow<ContactDetailsResponse> =
        repository.getContactDetails(contactId)

}