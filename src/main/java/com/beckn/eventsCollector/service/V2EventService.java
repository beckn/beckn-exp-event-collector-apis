package com.beckn.eventsCollector.service;

import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.mapper.V2EventDtoToEventMapper;
import com.beckn.eventsCollector.model.V2Event;
import com.beckn.eventsCollector.repository.V2EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.beckn.eventsCollector.model.Event.SEQUENCE_NAME;

@Service
public class V2EventService {
    @Autowired
    private V2EventRepository eventRepository;

    @Autowired
    private SequenceGeneratorService service;

    @Autowired
    private V2EventDtoToEventMapper eventDtoToEventMapper;

    public int saveOrUpdateEvent(V2EventDTO inputEvent) {
        V2Event event = eventDtoToEventMapper.mapEventDtoToEvent(inputEvent);
        event.setEvent_id(service.getSequenceNumber(SEQUENCE_NAME));
        return eventRepository.save(event).getEvent_id();
    }
}
