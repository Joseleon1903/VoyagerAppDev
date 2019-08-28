package com.voyager.app.rest.controller.opend;

import com.voyager.app.common.GenericErrorResponse;
import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.User;
import com.voyager.app.rest.dto.OtpFormDto;
import com.voyager.app.service.impl.ErrorExceptionServiceImpl;
import com.voyager.app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(OtpValidationController.VALIDATE_OTP_RESOURCE)
public class OtpValidationController {

    public static final String VALIDATE_OTP_RESOURCE= "/v1/api/validation/otp";

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ErrorExceptionServiceImpl errorExceptionService;

    @PostMapping
    public ResponseEntity displayOtpValidationForm(@Valid @RequestBody OtpFormDto otpForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            GenericErrorResponse reponse = new  GenericErrorResponse();
            reponse.setErrorCode(ConstantApplication.REQUIRED_FIELD_ERROR_CODE);
            reponse.setMessage("Existing field required");
            reponse.setStatus(HttpStatus.BAD_REQUEST);
            reponse.setTimestamp(new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
        }
        User userFound = userService.findByUsername(otpForm.getUsername()).get();
        if(otpForm.getOtpTextField().equals(userFound.getOtpEmailConfirmation().getOtpSending())){
            userFound.setStatus(ConstantApplication.STATUS_A);
            userFound.getOtpEmailConfirmation().setOtpValidated(true);
            userService.save(userFound);
            return ResponseEntity.ok().build();
        }
        String error = errorExceptionService.findByCode(ConstantApplication.INVALID_MATCH_OTP_ERROR_CODE).getDescription();
        GenericErrorResponse response = new  GenericErrorResponse();
        response.setErrorCode(ConstantApplication.INVALID_MATCH_OTP_ERROR_CODE);
        response.setMessage(error);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
