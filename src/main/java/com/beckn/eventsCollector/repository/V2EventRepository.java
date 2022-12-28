package com.beckn.eventsCollector.repository;

import com.beckn.eventsCollector.model.V2Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface V2EventRepository extends MongoRepository<V2Event, Integer> {
}
