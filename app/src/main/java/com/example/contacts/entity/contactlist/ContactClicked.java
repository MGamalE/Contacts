package com.example.contacts.entity.contactlist;

public class ContactClicked {
    private String id;
    private Boolean isClicked;

    public ContactClicked(String id, Boolean isClicked) {
        this.id = id;
        this.isClicked = isClicked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(Boolean isClicked) {
        this.isClicked = isClicked;
    }
}
