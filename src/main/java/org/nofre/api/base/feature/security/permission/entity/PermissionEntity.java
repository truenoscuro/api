package org.nofre.api.base.feature.security.permission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.security.grouppermission.entity.GroupPermissionEntity;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity extends CommonEntity {

    @Column(unique = true,
            nullable = false,
            updatable = false,
            length = 40)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_permission_id",
                nullable = false,
                updatable = false)
    private GroupPermissionEntity groupPermission;

}
