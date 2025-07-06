package org.nofre.api.base.feature.localization.labelgroup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "label_groups")
public class LabelGroupEntity extends CommonEntity {
    @Column(unique = true, nullable = false, length = 40)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "labelGroup")
    private Set<LabelEntity> labels = new HashSet<>();
}
