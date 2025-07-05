package org.nofre.api.base.feature.permission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.repository.CommonEntity;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity extends CommonEntity {

    @Column(unique = true, nullable = false, updatable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private String description;
}
