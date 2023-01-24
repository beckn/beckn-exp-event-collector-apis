package com.beckn.eventsCollector.repository;

import com.beckn.eventsCollector.model.V2Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface V2ApplicationRepository extends MongoRepository<V2Application, Integer> {
}
