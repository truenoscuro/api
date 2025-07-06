package org.nofre.api.base.feature.localization.translation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.repository.CommonEntity;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.language.entity.LanguageEntity;

@Getter
@Setter
@Entity
@Table(name = "translations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"language_id", "label_id"})
        }
)
public class TranslationEntity extends CommonEntity {


    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false)
    private LabelEntity label;

    @Column(nullable = false)
    private String value;


}
