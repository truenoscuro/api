package org.nofre.api.base.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class Paginated<T> {
    private final List<T> data;
    private final long totalElements;
    private final int totalPages;

    public Paginated(List<T> data, Page<?> page) {
        this.data = data;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

}
