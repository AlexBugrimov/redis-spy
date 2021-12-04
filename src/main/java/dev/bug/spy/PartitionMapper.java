package dev.bug.spy;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class PartitionMapper {

    public static <T> Page<T> toPage(Collection<T> content, int pageNumber, int pageSize) {
        int skipCount = (pageNumber - 1) * pageSize;
        Set<T> pageContent = content
                .stream()
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableSet());
        return Page.<T>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalResults(content.size())
                .totalPages(content.size() / pageSize)
                .content(pageContent)
                .build();
    }
}
