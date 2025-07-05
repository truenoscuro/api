package org.nofre.api.base.common.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class Paginated<T> {
    private final List<T> list;
    private final long totalElements;
    private final int totalPages;

    public Paginated(List<T> data, Page<?> page) {
        this.list = data;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

}
