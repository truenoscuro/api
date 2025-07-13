package org.nofre.api.base.feature.frontendresource;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.frontendresource.model.FrontendResourceDto;

import java.util.List;

public interface FrontendResourceService extends CommonCrudService<FrontendResourceDto> {
    List<FrontendResourceDto> findAllByMe(String email);
}

