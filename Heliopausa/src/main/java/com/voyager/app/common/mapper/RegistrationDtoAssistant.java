package com.voyager.app.common.mapper;

import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.OtpEmailConfirmation;
import com.voyager.app.entity.Profile;
import com.voyager.app.entity.Role;
import com.voyager.app.entity.User;
import com.voyager.app.exceptions.PasswordInvallidPatternException;
import com.voyager.app.rest.dto.RegistrationDTO;
import java.util.Date;

public class RegistrationDtoAssistant {

    public static void validationIntegrityPasswordField(RegistrationDTO registration) throws PasswordInvallidPatternException{

//        if (!AplicationUtil.patternCorrect(registration.getEmail(), ConstantAplication.EMAIL_REGEX)) {
//            throw new InvalidEmailException();
//        }
        if (registration.getPassword().matches(ConstantApplication.PASSWORD_REGEX) || registration.getConfirmPassword().matches(ConstantApplication.PASSWORD_REGEX)) {
            throw new PasswordInvallidPatternException();
        }
    }

    public static User convertToEntity(RegistrationDTO registration, Role role, String pass, String sentOtp, boolean emailSend) {
        User newUser =  new User();
        Profile newProfile = new Profile();
        newProfile.setFirstName(registration.getFirstName());
        newProfile.setLastName(registration.getLastName());
        newUser.setPassword(pass);
        newUser.setEmail(registration.getEmail());
        String[] username = registration.getEmail().split("@");
        newUser.setUsername(username[0]);
        newUser.setProfile(newProfile);
        newUser.getRoles().add(role);
        newUser.setStatus(ConstantApplication.STATUS_PA);
        newUser.setOtpEmailConfirmation(generateOtpEmail(sentOtp, emailSend));
        newUser.setCreationDate(new Date());
        return newUser;
    }

    public static OtpEmailConfirmation generateOtpEmail(String otp, boolean sendEmail){
        OtpEmailConfirmation output = new OtpEmailConfirmation();
        output.setOtpEmailSend(sendEmail);
        output.setOtpSending(otp);
        output.setOtpValidated(false);
        output.setOtpSendingDate(new Date());
        return output;
    }

}
