package org.nofre.api.base.common.crud.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
public enum FilterOperator {
    // Operadores básicos
    EQUALS("eq") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.equal(path, parseValue(value));
        }
    },
    NOT_EQUALS("neq") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.notEqual(path, parseValue(value));
        }
    },

    // Operadores de texto
    LIKE("like") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.like(cb.lower(path.as(String.class)),
                    "%" + value.toLowerCase() + "%");
        }
    },
    NOT_LIKE("nlike") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.notLike(cb.lower(path.as(String.class)),
                    "%" + value.toLowerCase() + "%");
        }
    },
    STARTS_WITH("sw") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.like(cb.lower(path.as(String.class)),
                    value.toLowerCase() + "%");
        }
    },
    ENDS_WITH("ew") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.like(cb.lower(path.as(String.class)),
                    "%" + value.toLowerCase());
        }
    },

    // Operadores numéricos
    GREATER_THAN("gt") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.greaterThan(path.as(BigDecimal.class), new BigDecimal(value));
        }
    },
    GREATER_THAN_OR_EQUAL("gte") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.greaterThanOrEqualTo(path.as(BigDecimal.class), new BigDecimal(value));
        }
    },
    LESS_THAN("lt") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.lessThan(path.as(BigDecimal.class), new BigDecimal(value));
        }
    },
    LESS_THAN_OR_EQUAL("lte") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.lessThanOrEqualTo(path.as(BigDecimal.class), new BigDecimal(value));
        }
    },
    // ... otros operadores ...

    BETWEEN_NUMBER("between") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            // Si no hay coma, tratarlo como un solo valor
            if (!value.contains(",")) {
                BigDecimal start = new BigDecimal(value.trim());
                return cb.greaterThanOrEqualTo(path.as(BigDecimal.class), start);
            }

            String[] numbers = value.split(",");
            BigDecimal start = new BigDecimal(numbers[0].trim());

            // Si hay un segundo número y no está vacío
            if (numbers.length > 1 && !numbers[1].trim().isEmpty()) {
                BigDecimal end = new BigDecimal(numbers[1].trim());
                return cb.between(path.as(BigDecimal.class), start, end);
            }

            // Si no hay segundo número o está vacío
            return cb.greaterThanOrEqualTo(path.as(BigDecimal.class), start);
        }
    },

    BETWEEN_DATE("between_date") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            // Si no hay coma, tratarlo como un solo valor
            if (!value.contains(",")) {
                LocalDateTime startDate = parseDateTime(value.trim());
                return cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), startDate);
            }

            String[] dates = value.split(",");
            LocalDateTime startDate = parseDateTime(dates[0].trim());

            // Si hay una segunda fecha y no está vacía
            if (dates.length > 1 && !dates[1].trim().isEmpty()) {
                LocalDateTime endDate = parseDateTime(dates[1].trim());
                return cb.between(path.as(LocalDateTime.class), startDate, endDate);
            }

            // Si no hay segunda fecha o está vacía
            return cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), startDate);
        }
    },

    // Operadores de fecha
    GREATER_THAN_DATE("gtd") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.greaterThan(path.as(LocalDateTime.class), parseDateTime(value));
        }
    },
    LESS_THAN_DATE("ltd") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.lessThan(path.as(LocalDateTime.class), parseDateTime(value));
        }
    },

    // Operadores de colección
    IN("in") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return path.in(Arrays.asList(value.split(",")));
        }
    },
    NOT_IN("nin") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return cb.not(path.in(Arrays.asList(value.split(","))));
        }
    },

    // Operadores nulos
    IS_NULL("null") {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value) {
            return Boolean.parseBoolean(value) ?
                    cb.isNull(path) :
                    cb.isNotNull(path);
        }
    };

    private final String code;

    FilterOperator(String code) {
        this.code = code;
    }

    public static FilterOperator fromCode(String code) {
        return Arrays.stream(FilterOperator.values())
                .filter(operator -> operator.getCode().equals(code))
                .findFirst()
                .orElse(EQUALS);
    }

    private static Object parseValue(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException ex) {
                return value;
            }
        }
    }

    private static LocalDateTime parseDateTime(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use ISO_DATE_TIME");
        }
    }

    public abstract Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, String value);

    // ... resto del código igual ...
}