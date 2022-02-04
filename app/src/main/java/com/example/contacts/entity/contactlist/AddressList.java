package com.example.contacts.entity.contactlist;

import com.google.gson.annotations.SerializedName;

public class AddressList {
    @SerializedName("address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
