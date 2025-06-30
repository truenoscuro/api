package org.nofre.api.base.common.crud;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.model.Paginated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Observed
@RequiredArgsConstructor
public abstract class CommonCrudService<
        E extends CommonEntity,
        D extends CommonDto,
        M extends CommonCrudMapper<E, D>,
        R extends CommonRepository<E>> implements
        ICommonCrudService<D> {

    protected final String tableName;
    protected final R repository;
    protected final M mapper;


    @Override
    public D getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IdCrudException(tableName, id));
    }

    @Override
    public D save(D dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }


    @Override
    public D update(D dto) {
        if (noExistById(dto.getId())) {
            throw new IdCrudException(tableName, dto.getId());
        }
        return save(dto);
    }

    @Override
    public void deleteById(Long id) {
        if (noExistById(id)) {
            throw new IdCrudException(tableName, id);
        }
        repository.deleteById(id);
    }

    //TODO aqui falta el objecto
    @Override
    public Paginated<D> getPaginated(TableContext context) {
        //TODO falta el filtro
        Page<E> page = repository.findAll(Pageable.ofSize(context.page()));
        return new Paginated<>(mapper.toDtoList(page.getContent()), page);
    }

    @Override
    public boolean noExistById(Long id) {
        return id == null || !repository.existsById(id);
    }

}

