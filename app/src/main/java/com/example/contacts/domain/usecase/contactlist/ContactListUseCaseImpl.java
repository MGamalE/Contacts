package com.example.contacts.domain.usecase.contactlist;

import com.example.contacts.domain.repository.contactlist.ContactListRepository;
import com.example.contacts.domain.repository.contactlist.ContactListRepositoryImpl;
import com.example.contacts.entity.contactlist.ContactListResponse;

import io.reactivex.Single;

public class ContactListUseCaseImpl implements ContactListUseCase {

    private ContactListRepository repository;

    public ContactListUseCaseImpl(ContactListRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Single<ContactListResponse> retrieveContactList(long page, long perPage) {
        return repository.retrieveContactList(page,perPage);
    }
}
