package dev.bug.spy;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class Page<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalResults;
    private Collection<T> content;
}
