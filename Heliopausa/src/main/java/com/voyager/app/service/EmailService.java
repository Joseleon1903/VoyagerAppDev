package com.voyager.app.service;

import freemarker.template.TemplateException;
import java.io.IOException;

public interface EmailService {

    void sentOtpValidationEmail(String otp, String destinationEmail) throws IOException, TemplateException;
}
