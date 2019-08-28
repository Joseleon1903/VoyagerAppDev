package com.voyager.app.rest.controller.opend;

import com.voyager.app.common.GenericErrorResponse;
import com.voyager.app.common.mapper.RegistrationDtoAssistant;
import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.Role;
import com.voyager.app.entity.User;
import com.voyager.app.rest.dto.RegistrationDTO;
import com.voyager.app.service.impl.EmailServiceImpl;
import com.voyager.app.service.impl.ErrorExceptionServiceImpl;
import com.voyager.app.service.impl.RoleServiceImpl;
import com.voyager.app.service.impl.UserServiceImpl;
import com.voyager.app.util.PasswordUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(RegistrationController.REGISTRATION_RESOURCE)
public class RegistrationController {

    public static final String REGISTRATION_RESOURCE= "/v1/api/registration";

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ErrorExceptionServiceImpl errorExceptionService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @PostMapping
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationDTO registerData, BindingResult bindingResult){

        //validando dto datos requeridos.
        if (bindingResult.hasErrors()){
            GenericErrorResponse reponse = new  GenericErrorResponse();
            reponse.setErrorCode(ConstantApplication.REQUIRED_FIELD_ERROR_CODE);
            reponse.setMessage("Existing field required");
            reponse.setStatus(HttpStatus.BAD_REQUEST);
            reponse.setTimestamp(new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
        }

        //validando passsword
        if(!registerData.getPassword().equals(registerData.getConfirmPassword())){
            String error = errorExceptionService.findByCode(ConstantApplication.INVALID_MATCH_PASSWORD_ERROR_CODE).getDescription();
            GenericErrorResponse response = new  GenericErrorResponse();
            response.setErrorCode(ConstantApplication.INVALID_MATCH_PASSWORD_ERROR_CODE);
            response.setMessage(error);
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setTimestamp(new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        //validando nombre usuario
        Optional<User> validationUser = userService.findByEmail(registerData.getEmail());
        if(validationUser.isPresent()){
            String error = errorExceptionService.findByCode(ConstantApplication.DUPLICATE_EMAIL_ERROR_CODE).getDescription();
            return ResponseEntity.badRequest().build();
        }

        //enviando email registration
        String otp = PasswordUtil.generatePassword(6);
        boolean sendEmail = true;
        try {
            emailServiceImpl.sentOtpValidationEmail(otp, registerData.getEmail());
        } catch (IOException | TemplateException e) {
            sendEmail=false;
            e.printStackTrace();
        }

        Role rol =  roleServiceImpl.findByName(ConstantApplication.ROLE_USER).get();
        String encodPass = bCryptPasswordEncoder.encode(registerData.getPassword());
        User newUser = RegistrationDtoAssistant.convertToEntity(registerData, rol, encodPass, otp, sendEmail);
        newUser = userService.save(newUser);
        newUser.setOtpEmailConfirmation(null);
        return ResponseEntity.ok().body(newUser);
    }

}
