package com.example.contacts.domain.gateway.remote;

import com.example.contacts.entity.contactlist.ContactListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContactAPI {
    @GET("contacts")
    Single<ContactListResponse> retrieveContactList(@Query("page") long page, @Query("per_page") long perPage);
}
