package org.nofre.api.base.feature.security.role.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.security.permission.entity.PermissionEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity extends CommonEntity {

    @Column(unique = true,
            nullable = false,
            updatable = false,
            length = 40)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private RoleEntity parent;

    @OneToMany(mappedBy = "parent")
    private Set<RoleEntity> children = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "roles_permissions",
               joinColumns = @JoinColumn(name = "role_id",
                                         referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "permission_id",
                                                referencedColumnName = "id"),
               uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})})
    private Set<PermissionEntity> permissions = new HashSet<>();


}
