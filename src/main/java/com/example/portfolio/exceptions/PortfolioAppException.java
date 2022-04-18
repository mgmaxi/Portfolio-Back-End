package com.example.portfolio.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PortfolioAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public PortfolioAppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PortfolioAppException(HttpStatus status, String message, String message1) {
        this.status = status;
        this.message = message;
        this.message = message1;
    }

}
