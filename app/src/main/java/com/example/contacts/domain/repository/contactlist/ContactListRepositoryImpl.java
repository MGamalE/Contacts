package com.example.contacts.domain.repository.contactlist;

import com.example.contacts.domain.gateway.remote.ContactAPI;
import com.example.contacts.entity.contactlist.ContactListResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ContactListRepositoryImpl implements ContactListRepository {

    private ContactAPI server;

    @Inject
    public ContactListRepositoryImpl(ContactAPI contactAPI) {
        this.server = contactAPI;
    }

    @Override
    public Single<ContactListResponse> retrieveContactList(long page, long perPage) {
        return server.retrieveContactList(page, perPage);
    }
}
