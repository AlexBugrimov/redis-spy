package dev.bug.spy.repository;

import java.util.List;

public interface RecordRepository<R, ID> {

    void saveAll(List<R> value);

    void deleteAll();

    List<R> findAll();
}
