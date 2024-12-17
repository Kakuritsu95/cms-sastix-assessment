package com.sastix.cms.server.services.email;

import com.sastix.cms.common.email.SendEmailDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface EmailService {
    public void sendEmailWithResourceAttachment(SendEmailDTO sendEmailDTO, Path resourcePath);
}
