package com.sastix.cms.common.content;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RevisionDTO {
    private ResourceDTO resourceDTO;
    private Date updatedAt;
}
