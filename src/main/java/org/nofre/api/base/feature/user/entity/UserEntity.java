package org.nofre.api.base.feature.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.security.role.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends CommonEntity {


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",                           // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(
                    name = "user_id",                          // Nombre de la columna que referencia a esta entidad
                    referencedColumnName = "id"                // Columna referenciada de esta entidad
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",                          // Nombre de la columna que referencia a la otra entidad
                    referencedColumnName = "id"                // Columna referenciada de la otra entidad
            ),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id", "role_id"})
            }
    )
    private Set<RoleEntity> roles = new HashSet<>();

}
