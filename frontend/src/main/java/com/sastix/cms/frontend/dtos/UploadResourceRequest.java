package com.sastix.cms.frontend.dtos;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@Slf4j

public class UploadResourceRequest {
    @NotNull
    private String resourceMediaType;

    @NotNull
    private String resourceAuthor;

    private MultipartFile resource;

    private String resourceExternalURI;

    @NotNull
    private String resourceName;

    @NotNull
    private String resourceTenantId;




}
