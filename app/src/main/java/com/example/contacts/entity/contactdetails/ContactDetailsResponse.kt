package com.example.contacts.entity.contactdetails

import com.google.gson.annotations.SerializedName

data class ContactDetailsResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val contactDetailsData: ContactDetailsData
)
