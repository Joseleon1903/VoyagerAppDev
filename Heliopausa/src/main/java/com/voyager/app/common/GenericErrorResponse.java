package com.voyager.app.common;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
public class GenericErrorResponse {

    // HTTP Response Status Code
    private  HttpStatus status;

    // General Error message
    private  String message;

    // Error code
    private  Long errorCode;

    private  Date timestamp;

    public GenericErrorResponse(){}

}
