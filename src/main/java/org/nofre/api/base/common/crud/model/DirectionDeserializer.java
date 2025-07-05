package org.nofre.api.base.common.crud.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class DirectionDeserializer extends JsonDeserializer<Sort.Direction> {
    @Override
    public Sort.Direction deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String value = p.getValueAsString().toUpperCase();
        try {
            return Sort.Direction.valueOf(value);
        } catch (IllegalArgumentException e) {
            return Sort.Direction.ASC; // valor por defecto
        }
    }
}