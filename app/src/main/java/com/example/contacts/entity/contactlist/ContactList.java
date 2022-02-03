package com.example.contacts.entity.contactlist;

import com.google.gson.annotations.SerializedName;

public class ContactList {
    @SerializedName("contact")
    private ContactListContactItem contactItem;

    public ContactListContactItem getContactItem() {
        return contactItem;
    }

    public void setContactItem(ContactListContactItem contactItem) {
        this.contactItem = contactItem;
    }
}
