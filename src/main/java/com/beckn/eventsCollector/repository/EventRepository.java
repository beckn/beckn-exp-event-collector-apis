package com.beckn.eventsCollector.repository;

import com.beckn.eventsCollector.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, Integer> {
}
