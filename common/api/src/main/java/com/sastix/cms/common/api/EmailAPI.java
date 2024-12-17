package com.sastix.cms.common.api;

import com.sastix.cms.common.email.SendEmailDTO;

public interface EmailAPI {

    public void sendEmailWithResourceAttachment(SendEmailDTO sendEmailDTO);
}
