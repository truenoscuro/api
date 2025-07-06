package org.nofre.api.base.common.crud.service;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.crud.controller.PaginatedSearch;
import org.nofre.api.base.common.crud.entity.CommonEntity;
import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.common.crud.exception.IdCrudException;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.common.crud.model.Paginated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Observed
@RequiredArgsConstructor
public abstract class CommonCrudServiceImp<
        E extends CommonEntity,
        D extends CommonDto,
        M extends CommonCrudMapper<E, D>,
        R extends CommonRepository<E>>
        implements CommonCrudService<D> {

    protected final String tableName;
    protected final R repository;
    protected final M mapper;
    protected final GenericSpecification<E> specification;


    @Override
    public D getById(Long id) throws CommonCrudException {
        return repository.findById(id)
                .map(mapper::toDtoWithRelations)
                .orElseThrow(() -> new IdCrudException(tableName, id));
    }

    @Override
    public D save(D dto) throws CommonCrudException {
        return mapper.toDto(repository.save(mapper.toEntityWithRelations(dto)));
    }


    @Override
    public D update(D dto) throws CommonCrudException {
        if (existById(dto.getId())) {
            throw new IdCrudException(tableName, dto.getId());
        }
        return save(dto);
    }

    @Override
    public void deleteById(Long id) throws CommonCrudException {
        if (existById(id)) {
            throw new IdCrudException(tableName, id);
        }
        repository.deleteById(id);
    }

    @Override
    public Paginated<D> getPaginatedList(
            Integer offset, Integer limit,
            String sort, String dir,
            Map<String, String> filters
    ) throws CommonCrudException {
        return getPaginatedList(offset, limit, sort, dir, specification.getSpecification(filters));
    }

    private Pageable getPageable(Integer offset, Integer limit, String sort, String dir) {
        Sort.Direction direction = Sort.Direction.fromString(dir.toUpperCase());
        return PageRequest.of(
                offset,
                limit,
                Sort.by(direction, sort)
        );
    }

    private Paginated<D> getPaginatedList(Integer offset, Integer limit,
                                          String sort, String dir,
                                          Specification<E> specification) throws CommonCrudException {


        Sort.Direction direction = Sort.Direction.fromString(dir.toUpperCase());
        Pageable pageable = PageRequest.of(
                offset,
                limit,
                Sort.by(direction, sort)
        );
        Page<E> page = repository.findAll(specification, pageable);
        return new Paginated<>(mapper.toDtoList(page.getContent()), page);

    }

    @Override
    public Paginated<D> getPaginatedList(PaginatedSearch filter) throws CommonCrudException {

        return getPaginatedList(
                filter.offset(),
                filter.limit(),
                filter.sort(),
                filter.dir(),
                specification.getSpecification(filter.filters()));
    }

    @Override
    public boolean existById(Long id) {
        return id != null && repository.existsById(id);
    }


}

