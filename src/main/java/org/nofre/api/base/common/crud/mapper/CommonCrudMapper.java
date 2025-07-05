package org.nofre.api.base.common.crud.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.common.crud.repository.CommonEntity;

import java.util.List;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCrudMapper<E extends CommonEntity, D extends CommonDto> {

    @BasicMapping
    D toDto(E entity);

    @BasicMapping
    @InheritInverseConfiguration(name = "toDto")
    E toEntity(D dto);


    @IterableMapping(qualifiedBy = BasicMapping.class)
    List<E> toEntityList(List<D> dtoList);

    @IterableMapping(qualifiedBy = BasicMapping.class)
    List<D> toDtoList(List<E> entityList);


    @WithRelations
    D toDtoWithRelations(E entity);

    @WithRelations
    @InheritInverseConfiguration(name = "toDtoWithRelations")
    E toEntityWithRelations(D dto);


    @IterableMapping(qualifiedBy = WithRelations.class)
    List<E> toEntityListWithRelations(List<D> dtoList);

    @IterableMapping(qualifiedBy = WithRelations.class)
    List<D> toDtoListWithRelations(List<E> entityList);
}

