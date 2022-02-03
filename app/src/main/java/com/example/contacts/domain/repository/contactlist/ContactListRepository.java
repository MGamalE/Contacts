package com.example.contacts.domain.repository.contactlist;

import com.example.contacts.entity.contactlist.ContactListResponse;

import io.reactivex.Single;

public interface ContactListRepository {
    Single<ContactListResponse> retrieveContactList(long page, long perPage);
}
