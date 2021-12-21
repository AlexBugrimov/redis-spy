package dev.bug.spy.service;

import dev.bug.spy.model.SecurityData;
import dev.bug.spy.repository.RecordRepository;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
public record SecurityDataService(RecordRepository<SecurityData, String> repository,
                                  SecurityDataFactory dataFactory) {

    public void deleteAllRecords() {
        repository.deleteAll();
    }

    public void createRecordsFrom(long count) {
        repository.saveAll(LongStream.rangeClosed(1, count)
                .mapToObj(dataFactory::create).toList());
    }

    public PagedListHolder<SecurityData> findAll(PageRequest pageRequest) {
        List<SecurityData> allRecords = repository.findAll();
        PagedListHolder<SecurityData> listHolder = new PagedListHolder<>(allRecords);
        listHolder.setPage(pageRequest.getPageNumber());
        listHolder.setPageSize(pageRequest.getPageSize());
        return listHolder;
    }
}
