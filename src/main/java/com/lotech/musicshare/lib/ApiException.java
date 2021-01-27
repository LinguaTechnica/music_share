package com.lotech.musicshare.lib;

import java.util.ArrayList;
import java.util.List;

public class ApiException {
    protected String message;
    protected List<String> details = new ArrayList<>();

    ApiException(String message, String details) {
        this.message = message;
        this.details.add(details);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public void addDetails(String details) {
        this.details.add(details);
    }
}
