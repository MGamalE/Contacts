package com.example.contacts.entity.login;

public class LoginValidation {
    private String errorUserId;
    private String errorUserPassword;
    private boolean isValid;

    public LoginValidation() {
        errorUserId = "";
        errorUserPassword = "";
        isValid = false;
    }

    public String getErrorUserId() {
        return errorUserId;
    }

    public void setErrorUserId(String errorUserId) {
        this.errorUserId = errorUserId;
    }

    public String getErrorUserPassword() {
        return errorUserPassword;
    }

    public void setErrorUserPassword(String errorUserPassword) {
        this.errorUserPassword = errorUserPassword;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
