package dev.bug.spy.repository;

import dev.bug.spy.model.SecurityData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class SecurityDataRepository implements RecordRepository<SecurityData, String> {

    private static final java.lang.String KEY = "Session";
    private final RedisTemplate<String, SecurityData> template;

    public SecurityDataRepository(RedisTemplate<String, SecurityData> template) {
        this.template = template;
    }

    @Override
    public void saveAll(List<SecurityData> records) {
        try {
            template.opsForValue().multiSet(
                    records.stream()
                            .collect(Collectors.toMap(SecurityData::getKey, Function.identity()))
            );
            log.info("Create records: {}", records);
        } catch (Exception ex) {
            log.error("Failed to create security data", ex);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Delete all records");
        Set<String> keys = template.keys("*");
        if (CollectionUtils.isNotEmpty(keys)) {
            Long keyCount = template.delete(keys);
            log.info("Deleted {} keys", keyCount);
        }
    }

    @Override
    public List<SecurityData> findAll() {
        Set<String> keys = template.keys("*");
        if (CollectionUtils.isNotEmpty(keys)) {
            List<SecurityData> records = template.opsForValue().multiGet(keys);
            log.info("Get all records: {}", records);
            return records;
        }
        log.info("Database is empty");
        return List.of();
    }
}
