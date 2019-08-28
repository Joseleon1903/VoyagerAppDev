package com.voyager.app.rest.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class OtpFormDto {

    @NotBlank
    private String otpTextField;

    @NotBlank
    private String username;

    public OtpFormDto(){}

}
