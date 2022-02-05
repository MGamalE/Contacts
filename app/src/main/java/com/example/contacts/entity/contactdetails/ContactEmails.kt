package com.example.contacts.entity.contactdetails

import com.google.gson.annotations.SerializedName

data class ContactEmails(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("value")
    val value: String? = null,
)
