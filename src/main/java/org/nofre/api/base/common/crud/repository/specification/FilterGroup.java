package org.nofre.api.base.common.crud.repository.specification;

import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class FilterGroup {


    private Map<String, String> filters = new HashMap<>();

    private List<FilterGroup> groups = new ArrayList<>();

    public static Builder where(String key, Object value, FilterOperator operator) {
        return new Builder().addFilter(operator, key, value);
    }

    protected void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public static class Builder {


        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


        List<FilterGroup> groups = new ArrayList<>();
        Map<String, String> filters = new HashMap<>();

        private String getKey(String key, FilterOperator operator) {
            return key + "_" + operator.getCode();
        }

        private String getValue(Object object) {
            return String.valueOf(object);
        }

        public FilterGroup build() {
            FilterGroup filterGroup = new FilterGroup();
            filterGroup.filters = this.filters;
            filterGroup.groups = this.groups;

            return filterGroup;
        }


        public Builder addFilter(FilterOperator operator, String key, Object value) {
            this.filters.put(getKey(key, operator), getValue(value));
            return this;
        }


        public Builder equals(String key, Object value) {
            return addFilter(FilterOperator.EQUALS, key, value);
        }

        public Builder notEquals(String key, Object value) {
            return addFilter(FilterOperator.NOT_EQUALS, key, value);
        }

        public Builder like(String key, String value) {
            return addFilter(FilterOperator.LIKE, key, value);
        }

        public Builder notLike(String key, String value) {
            return addFilter(FilterOperator.NOT_LIKE, key, value);
        }

        public Builder startsWith(String key, String value) {
            return addFilter(FilterOperator.STARTS_WITH, key, value);
        }

        public Builder endsWith(String key, String value) {
            return addFilter(FilterOperator.ENDS_WITH, key, value);
        }

        public <N extends Number> Builder greaterThan(String key, N value) {
            return addFilter(FilterOperator.GREATER_THAN, key, value.toString());
        }

        public <N extends Number> Builder greaterThanOrEqual(String key, N value) {
            return addFilter(FilterOperator.GREATER_THAN_OR_EQUAL, key, value.toString());
        }

        public <N extends Number> Builder lessThan(String key, N value) {
            return addFilter(FilterOperator.LESS_THAN, key, value.toString());
        }

        public <N extends Number> Builder lessThanOrEqual(String key, N value) {
            return addFilter(FilterOperator.LESS_THAN_OR_EQUAL, key, value.toString());
        }

        public <N extends Number> Builder between(String key, N start, @Nullable N end) {
            return addFilter(FilterOperator.BETWEEN_NUMBER, key, start + "," + end);
        }

        public <T extends Temporal> Builder between(String key, T start, @Nullable T end) {
            return addFilter(FilterOperator.BETWEEN_DATE, key, formatTemporal(start) + "," + formatTemporal(end));
        }

        private <T extends Temporal> String formatTemporal(T temporal) {
            if (temporal == null) {
                return "";
            }
            return switch (temporal) {
                case LocalDate d -> DATE_FORMATTER.format(d);
                case LocalTime t -> TIME_FORMATTER.format(t);
                case LocalDateTime dt -> DATE_TIME_FORMATTER.format(dt);
                case Instant i -> i.toString(); // ISO-8601 por defecto
                case ZonedDateTime zdt -> zdt.format(DATE_TIME_FORMATTER);
                default -> throw new IllegalArgumentException("Tipo temporal no soportado: " + temporal.getClass());
            };
        }

        public <T extends Temporal> Builder greaterThanDate(String key, T value) {
            return addFilter(FilterOperator.GREATER_THAN_DATE, key, formatTemporal(value));
        }

        public <T extends Temporal> Builder lessThanDate(String key, T value) {
            return addFilter(FilterOperator.LESS_THAN_DATE, key, formatTemporal(value));
        }

        public Builder in(String key, Object... values) {
            if (values == null || values.length == 0) {
                return this;
            }
            String value = Arrays.stream(values).map(Object::toString).collect(Collectors.joining(","));
            return addFilter(FilterOperator.IN, key, value);
        }

        public Builder notIn(String key, Object... values) {
            if (values == null || values.length == 0) {
                return this;
            }
            String value = Arrays.stream(values).map(Object::toString).collect(Collectors.joining(","));
            return addFilter(FilterOperator.NOT_IN, key, value);
        }

        public Builder isNull(String key) {
            return addFilter(FilterOperator.IS_NULL, key, true);
        }

        public Builder notNull(String key) {
            return addFilter(FilterOperator.IS_NULL, key, false);
        }


        public Builder or(Function<Builder, Builder> builder) {
            this.groups.add(builder.apply(new Builder()).build());
            return this;
        }

        public Builder join(String key, Function<Builder, Builder> builder) {
            Builder joinBuilder = builder.apply(new Builder());

            //Mapeamos los filtros con la key del join adelante
            Map<String, String> filters = new HashMap<>();
            for (Map.Entry<String, String> entry : joinBuilder.filters.entrySet()) {
                String newKey = String.join(".", key, entry.getKey());
                filters.put(newKey, entry.getValue());
            }

            //Modificamos los grupos de or con la key
            List<FilterGroup> groups = new ArrayList<>();
            for (FilterGroup group : joinBuilder.groups) {
                groups.add(
                        join(key, b -> {
                            b.groups = group.groups;
                            b.filters = group.filters;
                            return b;
                        }).build()
                );
            }

            //Añadimos el builder con el cambio de nombre
            Builder newBuilder = new Builder();
            newBuilder.groups = groups;
            newBuilder.filters = filters;
            this.groups.add(newBuilder.build());
            return this;
        }


    }

}
