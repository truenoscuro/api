package org.nofre.api.base.feature.user.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.user.UserMapper;
import org.nofre.api.base.feature.user.UserService;
import org.nofre.api.base.feature.user.entity.UserEntity;
import org.nofre.api.base.feature.user.entity.UserRepository;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.user.exception.RegisterPasswordException;
import org.nofre.api.base.feature.user.model.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrudService
public class UserServiceImp extends CommonCrudServiceImp<UserEntity, UserDto, UserMapper, UserRepository> implements UserService {

    private final PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder, GenericSpecification<UserEntity> specification) {
        super("users", repository, mapper, specification);
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Guarda los datos del usuario en la base de datos. Convierte el objeto de transferencia de datos (DTO)
     * del usuario en una entidad, codifica la contraseña del usuario por seguridad y luego persiste la entidad
     * en el repositorio. Finalmente, la entidad guardada se convierte de nuevo a DTO y se devuelve.
     *
     * @param user el objeto de transferencia de datos del usuario que contiene los detalles a guardar
     * @return el objeto de transferencia de datos del usuario guardado con la información actualizada de la base de datos
     */
    @Override
    public UserDto save(UserDto user) throws RegisterPasswordException {
        //ES IMPORTANTE QUE AL  CANVIAR EL SAVE TAMBIEN CAMBIAMOS EL UPDATE
        UserEntity entity = mapper.toEntity(user);

        boolean havePassword = user.getPassword() != null && !user.getPassword().isBlank();

        if (user.getId() == null && !havePassword) {
            throw new RegisterPasswordException();
        }

        if (havePassword) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }

        return mapper.toDto(repository.save(entity));
    }


    @Override
    public UserDto getByEmail(String email) throws EmailException {
        return repository.findByEmail(email).map(mapper::toDtoWithRelations).orElseThrow(EmailException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean isFirstRegister() {
        return repository.count() == 0;
    }
}
