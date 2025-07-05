package org.nofre.api.base.common.crud.exception;

public class IdCrudException extends CommonCrudException {

    public IdCrudException(String tableName, Long id) {
        super("No existe el recurso con el identificador " + id + " en la tabla " + tableName);
    }

}
