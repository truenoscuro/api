package org.nofre.api.base.common.crud.service;

import io.micrometer.tracing.annotation.SpanTag;
import org.nofre.api.base.common.crud.controller.PaginatedSearch;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.common.crud.model.Paginated;

import java.util.Map;

public interface CommonCrudService<D extends CommonDto> {

    D getById(@SpanTag Long id) throws CommonCrudException;

    D save(D dto) throws CommonCrudException;

    D update(D dto) throws CommonCrudException;

    void deleteById(Long id) throws CommonCrudException;

    Paginated<D> getPaginatedList(
            Integer offset, Integer limit,
            String sort, String dir,
            Map<String, String> filters
    ) throws CommonCrudException;

    Paginated<D> getPaginatedList(PaginatedSearch filter) throws CommonCrudException;

    boolean existById(Long id) throws CommonCrudException;
}
