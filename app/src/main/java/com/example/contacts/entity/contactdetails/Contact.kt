package com.example.contacts.entity.contactdetails

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("title")
    val title:String?=null,
    @SerializedName("first_name")
    val firstName:String?=null,
    @SerializedName("last_name")
    val lastName:String?=null,
    @SerializedName("photo_url")
    val phoneUrl:String?=null,
    @SerializedName("company_name")
    val companyName:String?=null,
    @SerializedName("phones")
    val phones:List<ContactPhones>?=null,
    @SerializedName("address_list")
    val addresses:List<ContactAddress>?=null,
)
