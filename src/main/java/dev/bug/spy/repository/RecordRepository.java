package dev.bug.spy.repository;

import java.util.List;

public interface RecordRepository<R, ID> {

    boolean save(R value);

    long deleteAll();

    List<R> findAll();

    R findById(ID id);
}
