package dev.bug.spy.service;

import dev.bug.spy.DataBuilder;
import dev.bug.spy.model.SecurityData;
import dev.bug.spy.repository.SecurityDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public record RedisSpyService(SecurityDataRepository repository,
                              DataBuilder dataBuilder) {

    public Page<SecurityData> findAllBy(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    public void clearDatabase() {
        repository.deleteAll();
    }

    public void createDataFrom(int count) {
        List<SecurityData> dataList = IntStream.rangeClosed(1, count)
                .mapToObj(dataBuilder::buildSecurityData)
                .toList();
        repository.saveAll(dataList);
    }
}
