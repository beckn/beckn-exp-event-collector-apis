package com.beckn.eventsCollector.service;

import com.beckn.eventsCollector.dto.EventDTO;
import com.beckn.eventsCollector.mapper.EventDtoToEventMapper;
import com.beckn.eventsCollector.mapper.EventToEventDtoMapper;
import com.beckn.eventsCollector.model.Event;
import com.beckn.eventsCollector.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.beckn.eventsCollector.model.Event.SEQUENCE_NAME;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SequenceGeneratorService service;

    @Autowired
    private EventDtoToEventMapper eventDtoToEventMapper;

    @Autowired
    private EventToEventDtoMapper eventToEventDtoMapper;

    public EventDTO saveOrUpdateEvent(EventDTO inputEvent) {
        Event event = eventDtoToEventMapper.mapEventDtoToEvent(inputEvent);
        event.setEvent_id(service.getSequenceNumber(SEQUENCE_NAME));
        event = eventRepository.save(event);
        return eventToEventDtoMapper.mapEventToEventDto(event);
    }
}
