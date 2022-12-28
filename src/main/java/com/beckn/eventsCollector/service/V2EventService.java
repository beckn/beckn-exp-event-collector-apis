package com.beckn.eventsCollector.service;

import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.exception.EventException;
import com.beckn.eventsCollector.mapper.V2EventDtoToEventMapper;
import com.beckn.eventsCollector.model.EventMessage;
import com.beckn.eventsCollector.model.V2Event;
import com.beckn.eventsCollector.model.V2EventMessage;
import com.beckn.eventsCollector.model.V2Experience;
import com.beckn.eventsCollector.repository.V2EventMessageRepository;
import com.beckn.eventsCollector.repository.V2EventRepository;
import com.beckn.eventsCollector.repository.V2ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beckn.eventsCollector.model.Event.SEQUENCE_NAME;

@Service
public class V2EventService {
    @Autowired
    private V2EventRepository eventRepository;

    @Autowired
    private V2ExperienceRepository experienceRepository;

    @Autowired
    private V2EventMessageRepository eventMessageRepository;

    @Autowired
    private SequenceGeneratorService service;

    @Autowired
    private V2EventDtoToEventMapper eventDtoToEventMapper;

    public int saveOrUpdateEvent(V2EventDTO inputEvent) {
        V2Event event = eventDtoToEventMapper.mapEventDtoToEvent(inputEvent);
        event.setEvent_id(service.getSequenceNumber(SEQUENCE_NAME));
        return eventRepository.save(event).getEvent_id();
    }

    public String getLatestExperienceSession() {
        List<V2Experience> experiences = experienceRepository.findByActiveFlag(true);
        if (experiences.isEmpty()) {
            throw new EventException("Application error", HttpStatus.NOT_FOUND.toString(), "/event/experience", "Latest experience session could not be retrieved.");
        }
        return experiences.get(0).getExperience_id();
    }

    public EventMessage GetEventCodeDetails(String eventCode) {
        V2EventMessage v2EventMessage = eventMessageRepository.findByCode(eventCode);
        if (v2EventMessage == null) {
            throw new EventException("Application error", HttpStatus.NOT_FOUND.toString(), "/event/code/" + eventCode, "Event code details could not be retrieved.");
        }
        return new EventMessage(v2EventMessage.getCode(), v2EventMessage.getAction_message(),
                v2EventMessage.getBap_message(), v2EventMessage.getBpp_message());
    }
}
