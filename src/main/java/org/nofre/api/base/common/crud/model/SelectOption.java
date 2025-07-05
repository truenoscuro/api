package org.nofre.api.base.common.crud.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "label")
@EqualsAndHashCode(of = "value")
public class SelectOption<T> {
    private String label;
    private T value;
}
