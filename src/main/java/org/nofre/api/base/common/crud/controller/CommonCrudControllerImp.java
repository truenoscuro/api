package org.nofre.api.base.common.crud.controller;

import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.controller.CommonController;
import org.nofre.api.base.common.controller.model.CommonRq;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.common.crud.model.Paginated;
import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Observed
@RequiredArgsConstructor
public abstract class CommonCrudControllerImp<D extends CommonDto, S extends CommonCrudService<D>> extends CommonController {

    protected final S service;
    private final String authority;

    @ModelAttribute("authority")
    public String getAuthority() {
        return authority.toUpperCase().trim();
    }

    /**
     * Recupera un elemento individual por su ID.
     *
     * @param id el identificador único del elemento a recuperar
     * @return un {@code ResponseEntity} conteniendo un objeto {@code CommonRs<D>} con el elemento solicitado
     * @throws CommonCrudException si ocurre un error al recuperar el elemento
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority(#authority+'_READ') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<D>> getItem(
            @Parameter(hidden = true) @ModelAttribute("authority") String authority,
            @PathVariable Long id) throws CommonCrudException {
        return getResponse(service.getById(id));
    }

    /**
     * Recupera una lista paginada de entidades con soporte para filtrado, ordenación y paginación.
     *
     * @param offset  la posición inicial para el conjunto de resultados paginado, por defecto es 0
     * @param limit   el número máximo de resultados a devolver, por defecto es 50
     * @param sort    el campo por el cual ordenar los resultados, por defecto es "id"
     * @param dir     la dirección de ordenación, "ASC" para ascendente o "DESC" para descendente, por defecto es "ASC"
     * @param filters un mapa de filtros adicionales para aplicar a la consulta; opcional
     * @return un {@code ResponseEntity} conteniendo un {@code CommonRs<Paginated<D>>} con la lista paginada de entidades
     * @throws CommonCrudException si ocurre un error durante la recuperación de la lista paginada
     */
    @GetMapping
    @PreAuthorize("hasAuthority(#authority+'_READ') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<Paginated<D>>> getPaginatedList(
            @Parameter(hidden = true) @ModelAttribute("authority") String authority,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "50") Integer limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") String dir,
            @RequestParam(required = false) Map<String, String> filters) throws CommonCrudException {
        //ELiminamos los filtros que no vamos a usar
        if (!filters.isEmpty()) {
            filters.remove("offset");
            filters.remove("limit");
            filters.remove("sort");
            filters.remove("dir");
        }
        return paginatedResponse(service.getPaginatedList(offset, limit, sort, dir, filters));
    }

    /**
     * Guarda un nuevo elemento utilizando los datos proporcionados en la solicitud.
     *
     * @param rq el objeto {@code CommonRq<D>} que contiene los datos a guardar
     * @return un {@code ResponseEntity<CommonRs<D>>} que contiene el elemento guardado y detalles de la respuesta asociados
     * @throws CommonCrudException si ocurre un error durante la operación de guardado
     */
    @PostMapping
    @PreAuthorize("hasAuthority(#authority+'_CREATE') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<D>> saveItem(
            @Parameter(hidden = true) @ModelAttribute("authority") String authority,
            @RequestBody CommonRq<D> rq) throws CommonCrudException {
        return createdResponse(service.save(rq.data()));
    }

    /**
     * Actualiza un elemento existente utilizando los datos proporcionados en la solicitud.
     *
     * @param rq el objeto {@code CommonRq<D>} que contiene los datos a actualizar
     * @return un {@code ResponseEntity<CommonRs<D>>} que contiene el elemento actualizado y detalles de la respuesta asociados
     * @throws CommonCrudException si ocurre un error durante la operación de actualización
     */
    @PutMapping
    @PreAuthorize("hasAuthority(#authority+'_UPDATE') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<D>> updateItem(@Parameter(hidden = true) @ModelAttribute("authority") String authority, @RequestBody CommonRq<D> rq) throws CommonCrudException {
        return updatedResponse(service.update(rq.data()));
    }

    /**
     * Elimina un elemento identificado por su ID único.
     *
     * @param id el identificador único del elemento a eliminar
     * @return un {@code ResponseEntity<CommonRs<Void>>} indicando el resultado de la operación de eliminación,
     * típicamente una respuesta con código de estado HTTP 204 (Sin Contenido)
     * @throws CommonCrudException si ocurre un error durante el proceso de eliminación
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority(#authority+'_DELETE') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<Void>> deleteItem(@Parameter(hidden = true) @ModelAttribute("authority") String authority, @PathVariable Long id) throws CommonCrudException {
        service.deleteById(id);
        return deletedResponse();
    }


    /**
     * Ejecuta una operación de búsqueda basada en los criterios de filtrado proporcionados y devuelve una lista paginada de resultados.
     *
     * @param rq el objeto {@code CommonRq<CustomFilter>} que contiene los criterios de filtrado para la operación de búsqueda
     * @return un {@code ResponseEntity} que contiene un objeto {@code CommonRs<Paginated<D>>} con la lista paginada de resultados
     * @throws CommonCrudException si ocurre un error durante la ejecución de la operación de búsqueda
     */
    @PostMapping("search")
    @PreAuthorize("hasAuthority(#authority+'_READ') || hasRole('SUPER_ADMIN')")
    public ResponseEntity<CommonRs<Paginated<D>>> search(@Parameter(hidden = true) @ModelAttribute("authority") String authority, @RequestBody CommonRq<PaginatedSearch> rq) throws CommonCrudException {
        return postResponse(service.getPaginatedList(rq.data()));
    }

}
