package org.nofre.api.base.feature.frontendresource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.security.permission.entity.PermissionEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "frontend_resources",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"parent_id", "display_order"}), @UniqueConstraint(columnNames = {"parent_id", "angular_path"})})
public class FrontendResourceEntity extends CommonEntity {

    @Column(length = 100)
    private String label;

    @Column(length = 100)
    private String icon;

    @Column(length = 100,
            nullable = false)
    private String angularPath;

    @Column(length = 100)
    private String angularRedirectTo;

    @Column(length = 100)
    private String titlePage;


    @ColumnDefault("99")
    @Column(nullable = false,
            name = "display_order")
    private int order = 99;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private FrontendResourceEntity parent;

    @OneToMany(mappedBy = "parent")
    private Set<FrontendResourceEntity> children = new HashSet<>();


    @Column(length = 100)
    private String angularComponent;

    @ColumnDefault("false")
    @Column(nullable = false,
            name = "is_menu")
    private boolean menu = false;

    @ColumnDefault("prefix")
    @Column(nullable = false)
    private String angularPathMatch = "prefix";


    @ManyToMany
    @JoinTable(name = "frontend_resources_permissions",
               joinColumns = @JoinColumn(name = "frontend_resource_id",
                                         referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "permission_id",
                                                referencedColumnName = "id"),
               uniqueConstraints = {@UniqueConstraint(columnNames = {"frontend_resource_id", "permission_id"})})
    private Set<PermissionEntity> permissions = new HashSet<>();
}
