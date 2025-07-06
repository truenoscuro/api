package org.nofre.api.base.feature.localization.label.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.localization.label.LabelMapper;
import org.nofre.api.base.feature.localization.label.LabelService;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.label.entity.LabelRepository;
import org.nofre.api.base.feature.localization.label.model.LabelDto;

@CrudService
public class LabelServiceImp extends CommonCrudServiceImp<
        LabelEntity,
        LabelDto,
        LabelMapper,
        LabelRepository
        > implements LabelService {
    public LabelServiceImp(LabelRepository repository,
                           LabelMapper mapper,
                           GenericSpecification<LabelEntity> specification) {
        super("labels", repository, mapper, specification);
    }
}
