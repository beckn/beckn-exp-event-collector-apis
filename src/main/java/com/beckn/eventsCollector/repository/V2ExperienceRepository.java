package com.beckn.eventsCollector.repository;

import com.beckn.eventsCollector.model.V2Experience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface V2ExperienceRepository extends MongoRepository<V2Experience, String> {
    @Query(value = "{active :?0}", sort= "{last_modified_at:-1}")
    List<V2Experience> findByActiveFlag(Boolean active);
}
