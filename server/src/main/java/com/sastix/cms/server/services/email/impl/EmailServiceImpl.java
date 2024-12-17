package com.sastix.cms.server.services.email.impl;

import com.sastix.cms.common.email.SendEmailDTO;
import com.sastix.cms.server.services.content.GeneralFileHandlerService;
import com.sastix.cms.server.services.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final GeneralFileHandlerService fileHandlerService;
    @Value("${spring.mail.username}")
    private String applicationEmailAddress;

    @Override
    @Async
    public void sendEmailWithResourceAttachment(SendEmailDTO sendEmailDTO, Path resourcePath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true, "UTF-8");
            helper.setFrom(applicationEmailAddress);
            helper.setTo(sendEmailDTO.getTo());
            helper.setSubject(sendEmailDTO.getSubject());
            Context context = new Context();
            context.setVariable("emailData", sendEmailDTO);
            String process = templateEngine.process("email-template.html",context);
            helper.setText(process,true);
            byte[] resourceBytes = Files.readAllBytes(resourcePath);
            String mimeType = fileHandlerService.getMediaType(resourceBytes);
            helper.addAttachment("resource." + mimeType.split("/")[1], resourcePath.toFile());
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
