package com.example.contacts.presentation.feature.base;

import androidx.lifecycle.ViewModel;

import com.example.contacts.presentation.core.ErrorType;

import java.io.IOException;

import retrofit2.HttpException;
import retrofit2.Response;

public class BaseViewModel extends ViewModel {

    protected ErrorType parseError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            Response body = ((HttpException) throwable).response();

            if (body != null) {
                switch (body.code()) {
                    case 400:
                        return ErrorType.GENERIC_ERROR;
                    case 401:
                        return ErrorType.UNAUTHORIZED_ERROR;
                    case 403:
                        return ErrorType.FORBIDDEN_ERROR;
                    case 404:
                        return ErrorType.NOT_FOUND_ERROR;
                    case 409:
                        return ErrorType.UNEXPECTED_ERROR;
                    case 500:
                        return ErrorType.SERVER_ERROR;
                }
            } else {
                return ErrorType.UNEXPECTED_ERROR;
            }
        } else if (throwable instanceof IOException) {
            return ErrorType.CONNECTION_ERROR;
        }

        return ErrorType.UNEXPECTED_ERROR;
    }
}
