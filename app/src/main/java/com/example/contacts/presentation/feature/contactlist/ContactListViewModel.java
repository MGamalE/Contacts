package com.example.contacts.presentation.feature.contactlist;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contacts.domain.usecase.contactlist.ContactListUseCase;
import com.example.contacts.entity.baseresponse.BaseResponse;
import com.example.contacts.entity.contactlist.ContactList;
import com.example.contacts.entity.contactlist.ContactListContactItem;
import com.example.contacts.entity.contactlist.ContactListData;
import com.example.contacts.entity.contactlist.ContactListResponse;
import com.example.contacts.entity.login.LoginValidation;
import com.example.contacts.presentation.core.ErrorType;
import com.example.contacts.presentation.feature.base.BaseViewModel;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

@HiltViewModel
public class ContactListViewModel extends BaseViewModel {

    @Inject
    ContactListUseCase useCase;

    private MutableLiveData<Boolean> _loading;

    private MutableLiveData<ErrorType> _error;

    private MutableLiveData<HashMap<String, List<String>>> _contactList;

    private MutableLiveData<List<ContactListContactItem>> _contactItem;

    private MutableLiveData<ContactListContactItem> _contact;

    private CompositeDisposable disposable;

    @Inject
    public ContactListViewModel() {
        _loading = new MutableLiveData<>();
        _error = new MutableLiveData<>();
        _contactItem = new MutableLiveData<>();
        _contact = new MutableLiveData<>();
        _contactList = new MutableLiveData<>();
        disposable = new CompositeDisposable();
    }

    public LiveData<Boolean> stateLoading() {
        return _loading;
    }

    public LiveData<ErrorType> stateError() {
        return _error;
    }

    public LiveData<HashMap<String, List<String>>> stateSuccess() {
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

    public void contactListItemClicked(int position) {
        passContactItem(position);
    }

    private void passContactItem(int position) {
        if (_contactItem.getValue() != null) {
            _contact.postValue(_contactItem.getValue().get(position));
        }
    }

    public LiveData<ContactListContactItem> getContactClicked() {
        return _contact;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.remove(disposable);
    }
}
