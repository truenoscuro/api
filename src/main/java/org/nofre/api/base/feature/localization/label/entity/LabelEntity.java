package org.nofre.api.base.feature.localization.label.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.repository.CommonEntity;
import org.nofre.api.base.feature.localization.labelgroup.entity.LabelGroupEntity;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "labels")
public class LabelEntity extends CommonEntity {

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "label_group_id", nullable = false)
    private LabelGroupEntity labelGroup;

    @OneToMany(mappedBy = "label")
    private Set<TranslationEntity> translations = new HashSet<>();

}
