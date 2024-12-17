package com.sastix.cms.frontend.services;

import com.sastix.cms.common.content.CreateResourceDTO;
import com.sastix.cms.common.content.UpdateResourceDTO;
import com.sastix.cms.common.content.exceptions.ContentValidationException;
import com.sastix.cms.frontend.dtos.UpdateResourceRequest;
import com.sastix.cms.frontend.dtos.UploadResourceRequest;

import java.io.IOException;

public interface ResourceService {

    CreateResourceDTO convertUploadResourceRequestToCreateResourceDto(UploadResourceRequest request) throws IOException, ContentValidationException;

    UpdateResourceDTO convertUpdateResourceRequestToUpdateDTO(UpdateResourceRequest updateResourceRequest) throws IOException;


}
