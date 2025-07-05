package org.nofre.api.base.common.crud.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public abstract class CommonDto {
    private Long id;
    private boolean active;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
}