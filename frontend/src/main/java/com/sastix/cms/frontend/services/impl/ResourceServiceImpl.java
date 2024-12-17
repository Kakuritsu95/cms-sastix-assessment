package com.sastix.cms.frontend.services.impl;

import com.sastix.cms.common.content.CreateResourceDTO;
import com.sastix.cms.common.content.UpdateResourceDTO;
import com.sastix.cms.common.content.exceptions.ContentValidationException;
import com.sastix.cms.frontend.dtos.UpdateResourceRequest;
import com.sastix.cms.frontend.dtos.UploadResourceRequest;
import com.sastix.cms.frontend.services.ResourceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ModelMapper mapper;

    @Override
    public CreateResourceDTO convertUploadResourceRequestToCreateResourceDto(UploadResourceRequest uploadResourceRequest) throws IOException {
        validateResourceOrURIHaveValue(uploadResourceRequest.getResource(), uploadResourceRequest.getResourceExternalURI());
        CreateResourceDTO createResourceDTO = mapper.map(uploadResourceRequest, CreateResourceDTO.class);

        if (uploadResourceRequest.getResource().getSize()>0) {
            byte[] resourceBinary = uploadResourceRequest.getResource().getInputStream().readAllBytes();
            createResourceDTO.setResourceBinary(resourceBinary);
            createResourceDTO.setResourceExternalURI(null);
            createResourceDTO.setResourceMediaType(uploadResourceRequest.getResource().getContentType());
        }
        return createResourceDTO;
    }

    @Override
    public UpdateResourceDTO convertUpdateResourceRequestToUpdateDTO(UpdateResourceRequest updateResourceRequest) throws IOException {
        validateResourceOrURIHaveValue(updateResourceRequest.getResource(), updateResourceRequest.getResourceExternalURI());
        UpdateResourceDTO updateResourceDTO = new UpdateResourceDTO();
        if (updateResourceRequest.getResource().getSize()>0) {
            byte[] resourceBinary = updateResourceRequest.getResource().getInputStream().readAllBytes();
            updateResourceDTO.setResourceBinary(resourceBinary);
            updateResourceDTO.setResourceURI(updateResourceRequest.getExistingResourceURI());
            updateResourceDTO.setType(updateResourceRequest.getResource().getContentType());
        } else {
           updateResourceDTO.setResourceExternalURI(updateResourceRequest.getResourceExternalURI());
        }
        updateResourceDTO.setAuthor(updateResourceRequest.getAuthor());
        updateResourceDTO.setResourceAuthor(updateResourceRequest.getResourceAuthor());
        updateResourceDTO.setResourceUID(updateResourceRequest.getResourceUID());

        updateResourceDTO.setResourceName(updateResourceRequest.getResourceName());
        return updateResourceDTO;
    }

    private void validateResourceOrURIHaveValue(MultipartFile resource, String externalURI) {
        if (resource.getSize() == 0 && externalURI.isEmpty()) {
            throw new ContentValidationException("Field errors: one of the fields resource or resourceExternalURI should have a value");
        }
        else if (resource.getSize()!=0 && StringUtils.isNotEmpty(externalURI)){
            throw new ContentValidationException("Field errors: Either resource or resourceExternalURI may hold a value");
        }
    }
}