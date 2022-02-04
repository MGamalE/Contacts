package com.example.contacts.entity.contactlist;

import com.example.contacts.entity.baseresponse.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class ContactListResponse extends BaseResponse {

    @SerializedName("data")
    private ContactListData contactListData;

    public ContactListData getContactListData() {
        return contactListData;
    }

    public void setContactListData(ContactListData contactListData) {
        this.contactListData = contactListData;
    }
}
