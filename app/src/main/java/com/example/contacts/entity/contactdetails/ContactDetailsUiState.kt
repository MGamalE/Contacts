package com.example.contacts.entity.contactdetails

import com.example.contacts.presentation.core.ErrorType

data class ContactDetailsUiState(
    val contactData: ContactDetailsResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: ErrorType? = null
)
