package org.nofre.api.base.feature.security.grouppermission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.security.permission.entity.PermissionEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group_permissions")
public class GroupPermissionEntity extends CommonEntity {
    private String name;

    @OneToMany(mappedBy = "groupPermission")
    private Set<PermissionEntity> permissions = new HashSet<>();
}
