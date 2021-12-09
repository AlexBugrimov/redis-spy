package dev.bug.spy.service;

import dev.bug.spy.DataBuilder;
import dev.bug.spy.model.SecurityData;
import dev.bug.spy.repository.SecurityDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RedisSpyService {

    private final SecurityDataRepository repository;
    private final DataBuilder dataBuilder;

    public Page<SecurityData> findAllBy(int page, int pageSize) {
        return repository.findAll(Pageable.ofSize(pageSize).withPage(page));
    }

    public void clearDatabase() {
        repository.deleteAll();
    }

    public void initializeDatabase() {
        List<SecurityData> dataList = IntStream.range(1, 20)
                .mapToObj(dataBuilder::buildSecurityData)
                .collect(Collectors.toList());
        repository.saveAll(dataList);
    }
}
