package dev.bug.spy;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class Page<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalResults;
    private Collection<T> content;

    public static <T> Page<T> empty() {
        return toPage(Set.of(), 0, 0);
    }

    public static <T> Page<T> toPage(Collection<T> content, int pageNumber, int pageSize) {
        int skipCount = (pageNumber - 1) * pageSize;
        Set<T> pageContent = content
                .stream()
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toSet());
        int totalResults = content.size();
        return Page.<T>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalResults(totalResults)
                .totalPages(totalResults == 0 ? 0 : totalResults / pageSize)
                .content(pageContent)
                .build();
    }
}
