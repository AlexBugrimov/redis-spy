package dev.bug.spy.repository;

import dev.bug.spy.model.SecurityData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityDataRepository extends PagingAndSortingRepository<SecurityData, String> {
}
