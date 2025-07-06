package org.nofre.api.base.feature.configuration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.entity.CommonEntity;

@Getter
@Setter
@Entity
@Table(name = "configurations")
public class ConfigurationEntity extends CommonEntity {

    @Column(unique = true, nullable = false)
    public String key;

    @Column(nullable = false)
    public String value;
}
