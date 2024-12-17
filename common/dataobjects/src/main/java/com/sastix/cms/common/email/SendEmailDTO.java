package com.sastix.cms.common.email;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SendEmailDTO {
    @NotNull
    private String resourceUID;
    @NotNull
    private String resourceURI;
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String subject;
    @NotNull
    private String content;

}
