package com.sastix.cms.frontend.controllers;


import com.sastix.cms.client.impl.CmsClient;
import com.sastix.cms.common.email.SendEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@PropertySource("classpath:frontend-application.properties")
@Controller
@RequestMapping("${client.prefix}/email")
@RequiredArgsConstructor

public class SendEmailController {

    private final CmsClient cmsClient;
    @Value("${client.prefix}")
    private String clientPrefixURL;
    @PostMapping("/form")
    public String getEmailForm(Model model, @RequestParam String resourceUID, @RequestParam String resourceURI){
            model.addAttribute("resourceUID", resourceUID);
            model.addAttribute("resourceURI", resourceURI);
            return "email-form";
    }
    @PostMapping("/send")
    public RedirectView sendEmail(@Valid @ModelAttribute SendEmailDTO sendEmailDTO){
        cmsClient.sendEmailWithResourceAttachment(sendEmailDTO);
        return new RedirectView(clientPrefixURL + "/resources");
    }

}
