package org.nofre.api.base.common.crud.repository.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.nofre.api.base.common.crud.repository.CommonEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GenericSpecification<E extends CommonEntity> {

    public Specification<E> getSpecification(Map<String, String> filters) {
        FilterGroup group = new FilterGroup();
        group.setFilters(filters);
        return getSpecification(group);
    }


    /**
     * Construye una especificación a partir de un grupo de filtros.
     *
     * @param group grupo de filtros a aplicar
     * @return Specification construida o null si el grupo es nulo
     */
    public Specification<E> getSpecification(FilterGroup group) {
        if (group == null
                || group.getFilters() == null
                || group.getFilters().isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Añadir predicados de los filtros directos
            group.getFilters().forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    String fieldName = getFieldName(key);
                    FilterOperator operator = FilterOperator.fromCode(getOperator(key));
                    predicates.add(operator.buildPredicate(
                            criteriaBuilder,
                            getPath(root, fieldName),
                            value
                    ));
                }
            });
            Predicate spec = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            // Añadir predicados de los subgrupos
            for (FilterGroup subGroup : group.getGroups()) {
                Specification<E> subSpecification = getSpecification(subGroup);
                if (subSpecification != null) {
                    spec = criteriaBuilder.or(spec, subSpecification.toPredicate(root, query, criteriaBuilder));
                }
            }

            return spec;
        };

    }


    private String getOperator(String key) {
        if (key.contains("_")) {
            return key.substring(key.lastIndexOf("_") + 1);
        }
        return "eq";
    }

    private String getFieldName(String key) {
        if (key.contains("_")) {
            return key.substring(0, key.lastIndexOf("_"));
        }
        return key;
    }

    private Path<?> getPath(Root<?> root, String key) {
        try {
            String[] paths = key.split("\\.");
            if (paths.length == 1) {
                return root.get(paths[0]);
            }

            Join<?, ?> join = root.join(paths[0]);
            for (int i = 1; i < paths.length - 1; i++) {
                join = join.join(paths[i]);
            }
            return join.get(paths[paths.length - 1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar el path: " + key, e);
        }
    }
}