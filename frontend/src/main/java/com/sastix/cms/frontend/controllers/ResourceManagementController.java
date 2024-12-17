package com.sastix.cms.frontend.controllers;


import com.sastix.cms.client.impl.CmsClient;
import com.sastix.cms.common.content.*;
import com.sastix.cms.common.lock.LockDTO;
import com.sastix.cms.common.lock.NewLockDTO;
import com.sastix.cms.frontend.dtos.DeleteResourceRequest;
import com.sastix.cms.frontend.dtos.UpdateResourceRequest;
import com.sastix.cms.frontend.dtos.UploadResourceRequest;
import com.sastix.cms.frontend.services.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@PropertySource("classpath:frontend-application.properties")
@Controller
@RequiredArgsConstructor
@RequestMapping("${client.prefix}/resources")
public class ResourceManagementController {

private final CmsClient client;
private final ResourceService resourceService;
@Value("${client.prefix}")
private String clientPrefixURL;


    @GetMapping
    public String displayAllResources(Model model) {

        List<ResourceDTO> resources = client.getPersistedResources();
        model.addAttribute("resources", resources);
        return "management-resources";
    }

    @PostMapping
    ResponseEntity<String> createResource(@Valid @ModelAttribute UploadResourceRequest uploadResourceRequest) throws IOException {
         CreateResourceDTO createResourceDTO = resourceService.convertUploadResourceRequestToCreateResourceDto(uploadResourceRequest);
         ResourceDTO resourceDTO =  client.createResource(createResourceDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body("Created, successfully");
   }

    @PutMapping
    ResponseEntity<String> updateResource(@Valid @ModelAttribute UpdateResourceRequest updateResourceRequest) throws IOException {
             UpdateResourceDTO updateResourceDTO = resourceService.convertUpdateResourceRequestToUpdateDTO(updateResourceRequest);
             NewLockDTO newLockDTO = new NewLockDTO(updateResourceRequest.getResourceUID(), updateResourceRequest.getAuthor());
             LockDTO lockDTO = client.lockResource(newLockDTO);
             updateResourceDTO.setLockID(lockDTO.getLockID());
             LockedResourceDTO lockedResourceDTO = client.updateResource(updateResourceDTO);
             client.unlockResource(lockedResourceDTO);
             return ResponseEntity.ok("Updated, successfully");
    }
    @DeleteMapping
    ResponseEntity<String> deleteResource(@Valid @RequestBody DeleteResourceRequest deleteResourceRequest){
        NewLockDTO newLockDTO = new NewLockDTO(deleteResourceRequest.getResourceUID(), deleteResourceRequest.getResourceAuthor());
        LockDTO lockDTO = client.lockResource(newLockDTO);
        LockedResourceDTO lockedResourceDTO = new LockedResourceDTO(newLockDTO.getUID(), newLockDTO.getLockOwner(), lockDTO.getLockID(),lockDTO.getLockExpiration());
        client.deleteResource(lockedResourceDTO);
        return ResponseEntity.ok("Deleted, successfully");
    }

    @GetMapping("/revisions/{resourceId}")
    String getResourceRevisions(@PathVariable Integer resourceId, Model model){
        List<RevisionDTO> revisions = client.getResourceRevisions(resourceId);
        model.addAttribute("revisions", revisions);
        model.addAttribute("resourceId", resourceId);
        model.addAttribute("clientPrefixURL", clientPrefixURL);
        return "revisions";

    }



}

