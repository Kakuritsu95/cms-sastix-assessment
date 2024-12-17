package com.sastix.cms.frontend.dtos;

import lombok.Data;

@Data
public class DeleteResourceRequest {
    private String resourceUID;
    private String resourceAuthor;
}
