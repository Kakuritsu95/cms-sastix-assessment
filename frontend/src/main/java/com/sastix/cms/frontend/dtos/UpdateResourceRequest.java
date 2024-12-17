package com.sastix.cms.frontend.dtos;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@Slf4j

public class UpdateResourceRequest {

    @NotNull
    private String resourceAuthor;

    @NotNull
    private String resourceUID;

    @NotNull
    private String author;

    @NotNull
    private String existingResourceURI;

    private MultipartFile resource;


    private String resourceExternalURI;


    private String resourceName;




}
