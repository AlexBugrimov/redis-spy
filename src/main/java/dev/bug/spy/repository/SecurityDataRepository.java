package dev.bug.spy.repository;

import dev.bug.spy.model.SecurityData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Repository
public class SecurityDataRepository implements RecordRepository<SecurityData, String> {

    private static final java.lang.String KEY = "Session";
    private final RedisTemplate<String, SecurityData> template;

    public SecurityDataRepository(RedisTemplate<String, SecurityData> template) {
        this.template = template;
    }

    @Override
    public void save(SecurityData securityData) {
        try {
            template.opsForHash().put(KEY, securityData.getKey(), securityData);
            log.info("Create security data: {}", securityData);
        } catch (Exception ex) {
            log.error("Failed to create security data", ex);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Delete all records");
        Set<String> keys = template.keys("*");
        if (CollectionUtils.isNotEmpty(keys)) {
            Long deletedCount = template.delete(keys);
            log.info("Deleted {} keys", deletedCount);
        }
    }

    @Override
    public List<SecurityData> findAll() {
        log.info("Find all records");
        return template.opsForHash().values(KEY).stream()
                .filter(Objects::nonNull)
                .map(SecurityData.class::cast)
                .toList();
    }
}
