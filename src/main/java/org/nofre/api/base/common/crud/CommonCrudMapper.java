package org.nofre.api.base.common.crud;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CommonCrudMapper<E extends CommonEntity, D extends CommonDto> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDtoList(List<E> entityList);

}
