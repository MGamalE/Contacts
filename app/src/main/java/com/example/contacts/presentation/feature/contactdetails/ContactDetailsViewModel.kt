package com.example.contacts.presentation.feature.contactdetails

import androidx.lifecycle.viewModelScope
import com.example.contacts.domain.usecase.contactdetails.ContactDetailsUseCase
import com.example.contacts.entity.contactdetails.ContactDetailsUiState
import com.example.contacts.presentation.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(private val useCase: ContactDetailsUseCase) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(ContactDetailsUiState(isLoading = true))
    val uiState: StateFlow<ContactDetailsUiState> = _uiState

    fun retrieveContactDetails(contactId: String) {
        _uiState.update { ContactDetailsUiState(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCase.getContactDetails(contactId).collect { response ->
                    _uiState.update {
                        ContactDetailsUiState(
                            contactData = response,
                            isLoading = false
                        )
                    }
                }

            } catch (t: Throwable) {
                _uiState.update {
                    ContactDetailsUiState(
                        errorMessage = parseError(t),
                        isLoading = false
                    )
                }
            }
        }
    }
}