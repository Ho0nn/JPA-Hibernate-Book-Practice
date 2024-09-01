package com.jpabooks.errors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private Boolean sucess;
    private String message;
    private LocalDateTime dateTime;
    private List<String> details;

    public ErrorResponse() {}

    public ErrorResponse(String message, List<String> details) {
       super();
        this.message = message;
        this.details = details;
        this.sucess =Boolean.FALSE;
        this.dateTime=LocalDateTime.now();
    }

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
