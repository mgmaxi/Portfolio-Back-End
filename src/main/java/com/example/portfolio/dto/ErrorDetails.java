package com.example.portfolio.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

    private Date timeMark;
    private String message;
    private String details;

    public ErrorDetails() {
    }

    public ErrorDetails(Date timeMark, String message, String details) {
        this.timeMark = timeMark;
        this.message = message;
        this.details = details;
    }

}
