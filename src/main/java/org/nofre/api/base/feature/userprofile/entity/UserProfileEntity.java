package org.nofre.api.base.feature.userprofile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.user.entity.UserEntity;

@Getter
@Setter
@Entity
@Table(name = "user_profiles")
public class UserProfileEntity extends CommonEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String firstSurname;

    private String secondSurname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
