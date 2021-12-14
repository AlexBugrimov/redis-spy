package dev.bug.spy.repository;

import dev.bug.spy.model.SecurityData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class SecurityDataRepository implements RecordRepository<SecurityData, String> {

    private static final String KEY = "Session";
    private final RedisTemplate<String, SecurityData> template;

    public SecurityDataRepository(RedisTemplate<String, SecurityData> template) {
        this.template = template;
    }

    @Override
    public boolean save(SecurityData securityData) {
        try {
            template.opsForHash().put(KEY, securityData.getKey(), securityData);
            log.info("Create security data: {}", securityData);
            return true;
        } catch (Exception ex) {
            log.error("Failed to create security data", ex);
            return false;
        }
    }

    @Override
    public long deleteAll() {
        log.info("Delete all records");
        return template.opsForHash().delete(KEY);
    }

    @Override
    public List<SecurityData> findAll() {
        log.info("Find all records");
        return template.opsForHash().values(KEY).stream()
                .filter(Objects::nonNull)
                .map(SecurityData.class::cast)
                .toList();
    }

    @Override
    public SecurityData findById(String id) {
        log.info("Find record by id: {}", id);
        return (SecurityData) template.opsForHash().get(KEY, id);
    }
}
