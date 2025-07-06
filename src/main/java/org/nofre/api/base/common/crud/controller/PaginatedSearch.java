package org.nofre.api.base.common.crud.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.nofre.api.base.common.crud.entity.specification.FilterGroup;

public record PaginatedSearch(
        @JsonProperty(defaultValue = "0")
        Integer offset,
        @JsonProperty(defaultValue = "50")
        Integer limit,
        @JsonProperty(defaultValue = "id")
        String sort,
        @JsonProperty(defaultValue = "ASC")
        String dir,
        FilterGroup filters
) {


}
