package com.example.contacts.entity.contactdetails

import com.google.gson.annotations.SerializedName

data class ContactAddress(
    @SerializedName("address")
    val address: String? = null,
)
