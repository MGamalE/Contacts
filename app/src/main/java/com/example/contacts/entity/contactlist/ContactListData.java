package com.example.contacts.entity.contactlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactListData {
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("contacts")
    private List<ContactList> contactList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<ContactList> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactList> contactList) {
        this.contactList = contactList;
    }
}
