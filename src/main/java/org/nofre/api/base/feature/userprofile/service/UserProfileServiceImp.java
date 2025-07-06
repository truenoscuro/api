package org.nofre.api.base.feature.userprofile.service;

import org.nofre.api.base.common.crud.repository.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.userprofile.UserProfileMapper;
import org.nofre.api.base.feature.userprofile.UserProfileService;
import org.nofre.api.base.feature.userprofile.entity.UserProfileEntity;
import org.nofre.api.base.feature.userprofile.entity.UserProfileRepository;
import org.nofre.api.base.feature.userprofile.model.UserProfileDto;

@CrudService
public class UserProfileServiceImp extends CommonCrudServiceImp<
        UserProfileEntity,
        UserProfileDto,
        UserProfileMapper,
        UserProfileRepository
        > implements UserProfileService {

    public UserProfileServiceImp(UserProfileRepository repository,
                                 UserProfileMapper mapper,
                                 GenericSpecification<UserProfileEntity> specification) {
        super("user_profiles", repository, mapper, specification);
    }


    @Override
    public UserProfileDto getByEmail(String email) throws EmailException {
        return repository.findByUser_Email(email)
                .map(mapper::toDto)
                .orElseThrow(EmailException::new);
    }

}
