package com.voyager.app.service.impl;

import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    @Async
    public void sendEmail(final String destinationEmail, final String content, final String subject, final boolean isHtml) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(ConstantApplication.FROM_EMAIL);
                helper.setSubject(subject);
                helper.setTo(destinationEmail);
                // use the true flag to indicate you need a multipart message
                helper.setText(content, isHtml);
            }
        };
        emailSender.send(preparator);
    }

    @Override
    public void sentOtpValidationEmail(String otp, String destinationEmail) throws IOException, TemplateException{
        Map model = new HashMap();
        model.put("emailCode", otp);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                freemarkerConfiguration.getTemplate("fm_email_verification_code.ftl"), model);
        sendEmail(destinationEmail,content, "validation Otp", true);
    }
}
