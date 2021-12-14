package dev.bug.spy.service;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
public record SecurityDataService(RecordRepository<SecurityData, String> repository,
                                  SecurityDataFactory securityDataProvider) {

    public long deleteAllRecords() {
        return repository.deleteAll();
    }

    public Page<SecurityData> findAll(Pageable pageable) {
        List<SecurityData> allRecords = repository.findAll();
        return new PageImpl<>(allRecords, pageable, allRecords.size());
    }

    public SecurityData findById(String id) {
        return repository.findById(id);
    }

    public long createRecordsFrom(long count) {
        return LongStream.rangeClosed(1, count)
                .mapToObj(securityDataProvider::create)
                .map(repository::save)
                .count();
    }
}
