package com.example.contacts.entity.contactdetails

import com.google.gson.annotations.SerializedName

data class ContactDetailsData(
    @SerializedName("contact")
    val contact:Contact
)
