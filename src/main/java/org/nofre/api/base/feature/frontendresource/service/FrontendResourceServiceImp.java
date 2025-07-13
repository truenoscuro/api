package org.nofre.api.base.feature.frontendresource.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.frontendresource.FrontendResourceMapper;
import org.nofre.api.base.feature.frontendresource.FrontendResourceService;
import org.nofre.api.base.feature.frontendresource.entity.FrontendResourceEntity;
import org.nofre.api.base.feature.frontendresource.entity.FrontendResourceRepository;
import org.nofre.api.base.feature.frontendresource.model.FrontendResourceDto;

import java.util.Comparator;
import java.util.List;

@CrudService
public class FrontendResourceServiceImp extends CommonCrudServiceImp<FrontendResourceEntity, FrontendResourceDto, FrontendResourceMapper, FrontendResourceRepository> implements FrontendResourceService {

    public FrontendResourceServiceImp(FrontendResourceRepository repository, FrontendResourceMapper mapper, GenericSpecification<FrontendResourceEntity> specification) {
        super("frontend_resources", repository, mapper, specification);
    }


    @Override
    public List<FrontendResourceDto> findAllByMe(String email) {
        List<FrontendResourceDto> frontendResources = mapper.toDtoListWithRelations(repository.findAllByMe(email));
        frontendResources.forEach(this::orderChildren);
        return frontendResources;
    }

    private void orderChildren(FrontendResourceDto dto) {
        dto.getChildren().sort(Comparator.comparingInt(FrontendResourceDto::getOrder));
        dto.getChildren().forEach(this::orderChildren);
    }


}
