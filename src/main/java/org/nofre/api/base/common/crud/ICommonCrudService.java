package org.nofre.api.base.common.crud;

import io.micrometer.tracing.annotation.SpanTag;
import org.nofre.api.base.common.model.Paginated;

public interface ICommonCrudService<D extends CommonDto> {

    D getById(@SpanTag Long id);

    D save(D dto);

    D update(D dto);

    void deleteById(Long id);

    //TODO aqui falta el objecto
    Paginated<D> getPaginated(TableContext context);

    boolean noExistById(Long id);
}
