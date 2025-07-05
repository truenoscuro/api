package org.nofre.api.base.common.crud.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GenericSpecification<E extends CommonEntity> {

    public Specification<E> getSpecificationFromFilters(Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    String fieldName = getFieldName(key);
                    FilterOperator operator = FilterOperator.fromCode(getOperator(key));

                    predicates.add(
                            operator.buildPredicate(
                                    criteriaBuilder,
                                    root.get(fieldName),
                                    value
                            )
                    );
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
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
}