package org.nofre.api.base.common.crud;

public class IdCrudException extends RuntimeException {

    public IdCrudException(String tableName, Long id) {
        super("No existe el recurso con el identificador " + id + " en la tabla " + tableName);
    }

}
