package com.example.contacts.presentation.feature.contactlist;

import android.annotation.SuppressLint;

import com.example.contacts.domain.usecase.contactlist.ContactListUseCase;
import com.example.contacts.entity.contactlist.ContactClicked;
import com.example.contacts.entity.contactlist.ContactListContactItem;
import com.example.contacts.entity.contactlist.ContactListResponse;
import com.example.contacts.presentation.core.ErrorType;
import com.example.contacts.presentation.core.SingleLiveEvent;
import com.example.contacts.presentation.feature.base.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class ContactListViewModel extends BaseViewModel {

    @Inject
    ContactListUseCase useCase;

    private SingleLiveEvent<Boolean> _loading;

    private SingleLiveEvent<ErrorType> _error;

    private SingleLiveEvent<HashMap<String, List<String>>> _contactList;

    private SingleLiveEvent<List<ContactListContactItem>> _contactItem;

    private SingleLiveEvent<ContactClicked> _contact;

    private CompositeDisposable disposable;

    @Inject
    public ContactListViewModel() {
        _loading = new SingleLiveEvent<>();
        _error = new SingleLiveEvent<>();
        _contactItem = new SingleLiveEvent<>();
        _contact = new SingleLiveEvent<>();
        _contactList = new SingleLiveEvent<>();
        disposable = new CompositeDisposable();
    }

    public SingleLiveEvent<Boolean> stateLoading() {
        return _loading;
    }

    public SingleLiveEvent<ErrorType> stateError() {
        return _error;
    }

    public SingleLiveEvent<HashMap<String, List<String>>> stateSuccess() {
        return _contactList;
    }

    @SuppressLint("CheckResult")
    public void retrieveContacts(long page, long perPage) {
        _loading.postValue(true);
        disposable.add(useCase.retrieveContactList(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveDataSate, this::saveErrorState));
    }

    private void saveDataSate(ContactListResponse response) {
        _loading.postValue(false);
        _contactList.postValue(mapContactList(response));
    }

    private HashMap<String, List<String>> mapContactList(ContactListResponse response) {
        HashMap<String, List<String>> contactGroupName = new HashMap<>();
        List<ContactListContactItem> contacts = new ArrayList<>();

        for (int i = 0; i < response.getContactListData().getContactList().size(); i++) {
            contacts.add(response.getContactListData().getContactList().get(i).getContactItem());

            //save each contact from response list to assign it later when user click
            _contactItem.postValue(contacts);

            //Initialize list with each iteration
            List<String> contactsContent = new ArrayList<>();

            //Add contact address to contacts
            contactsContent.add(response
                    .getContactListData()
                    .getContactList()
                    .get(i)
                    .getContactItem()
                    .getAddressList().get(0).getAddress());

            //Push header and body to expandable list map
            contactGroupName.put(response
                    .getContactListData()
                    .getContactList()
                    .get(i)
                    .getContactItem()
                    .getFirstName() + " " + response
                    .getContactListData()
                    .getContactList()
                    .get(i)
                    .getContactItem()
                    .getLastName(), contactsContent);
        }

        return contactGroupName;
    }

    private void saveErrorState(Throwable error) {
        _loading.postValue(false);
        _error.postValue(parseError(error));
    }

    public void contactListItemClicked(int position, Boolean clicked) {
        passContactItem(position, clicked);
    }

    private void passContactItem(int position, Boolean clicked) {
        if (_contactItem.getValue() != null) {
            _contact.postValue(new ContactClicked(_contactItem.getValue().get(position).getId(),
                    clicked));
        }
    }

    public SingleLiveEvent<ContactClicked> getContactClicked() {
        return _contact;
    }

    public void release() {
        _contactList = new SingleLiveEvent<>();
        _contact.postValue(new ContactClicked("",false));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.remove(disposable);
    }
}
