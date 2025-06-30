package org.nofre.api.base.common.crud;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.Sort;

import java.util.Map;

public record TableContext(
        int page,
        int size,
        String sort,
        @JsonDeserialize(using = DirectionDeserializer.class)
        Sort.Direction direction,
        Map<String, ?> filters
) {

}
