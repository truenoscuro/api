package org.nofre.api.configuration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nofre.api.base.common.crud.CommonEntity;

@Getter
@Setter
@Entity
@Table(name = "configuration")
public class ConfigurationEntity extends CommonEntity {

    @Column(unique = true)
    public String key;


    public String value;
}
