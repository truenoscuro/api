package org.nofre.api.base.feature.localization.language.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "languages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code", "locale"})
        }
)
public class LanguageEntity extends CommonEntity {

    @Column(nullable = false, length = 2)
    private String code;

    @Column(nullable = false, length = 20)
    private String locale;

    @Column(nullable = false, length = 40)
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<TranslationEntity> translations;
}
