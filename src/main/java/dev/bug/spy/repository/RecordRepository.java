package dev.bug.spy.repository;

import java.util.List;

public interface RecordRepository<R, ID> {

    void save(R value);

    void deleteAll();

    List<R> findAll();
}
