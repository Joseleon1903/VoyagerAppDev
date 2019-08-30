package com.voyager.app.rest.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegistrationDTO implements Serializable {

    private static final long serialVersionUID = 8297240807703876682L;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    public String email;

    public RegistrationDTO(){}

}