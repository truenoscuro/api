package org.nofre.api.base.common.crud;

import org.nofre.api.base.common.controller.CommonRq;
import org.nofre.api.base.common.controller.CommonRs;
import org.nofre.api.base.common.model.Paginated;
import org.springframework.http.ResponseEntity;

public interface ICommonCrudController<D extends CommonDto> {

    ResponseEntity<CommonRs<D>> getItem(Long id);

    ResponseEntity<CommonRs<Paginated<D>>> getPaginated(CommonRq<TableContext> rq);

    ResponseEntity<CommonRs<D>> saveItem(CommonRq<D> rq);

    ResponseEntity<CommonRs<D>> updateItem(CommonRq<D> rq);

    ResponseEntity<CommonRs<Void>> deleteItem(Long id);



}
