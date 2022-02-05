package com.example.contacts.domain.usecase.contactlist;

import com.example.contacts.entity.contactlist.ContactListResponse;

import io.reactivex.Single;

public interface ContactListUseCase {
    Single<ContactListResponse> retrieveContactList(long page, long perPage);
}
