package com.sastix.cms.server.controllers;

import com.sastix.cms.common.Constants;
import com.sastix.cms.common.content.DataDTO;
import com.sastix.cms.common.email.SendEmailDTO;
import com.sastix.cms.server.CmsServer;
import com.sastix.cms.server.services.content.HashedDirectoryService;
import com.sastix.cms.server.services.content.ResourceService;
import com.sastix.cms.server.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/" + CmsServer.CONTEXT)

public class EmailController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/v" + Constants.REST_API_1_0 + "/" + Constants.SEND_EMAIL_ATTACHMENT, method = RequestMethod.POST)
    public void sendEmailAttachment(@RequestBody SendEmailDTO sendEmailDTO) throws IOException {
       DataDTO data = new DataDTO();
       data.setResourceURI(sendEmailDTO.getResourceURI());
       Path path =  resourceService.getDataPath(data);
       emailService.sendEmailWithResourceAttachment(sendEmailDTO, path);
    }

}
