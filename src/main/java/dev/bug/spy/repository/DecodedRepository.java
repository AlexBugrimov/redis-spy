package dev.bug.spy.repository;

import dev.bug.spy.model.Decoded;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecodedRepository extends PagingAndSortingRepository<Decoded, String> {
}
