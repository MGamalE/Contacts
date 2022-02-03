package com.example.contacts.entity.contactlist;

import com.google.gson.annotations.SerializedName;

public class ContactListResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ContactListData contactListData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactListData getContactListData() {
        return contactListData;
    }

    public void setContactListData(ContactListData contactListData) {
        this.contactListData = contactListData;
    }
}
