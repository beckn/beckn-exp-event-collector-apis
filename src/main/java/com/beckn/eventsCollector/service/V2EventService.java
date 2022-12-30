package com.beckn.eventsCollector.service;

import com.beckn.eventsCollector.cache.DatabaseCache;
import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.exception.EventException;
import com.beckn.eventsCollector.mapper.V2EventDtoToEventMapper;
import com.beckn.eventsCollector.model.*;
import com.beckn.eventsCollector.repository.V2ApplicationRepository;
import com.beckn.eventsCollector.repository.V2EventMessageRepository;
import com.beckn.eventsCollector.repository.V2EventRepository;
import com.beckn.eventsCollector.repository.V2ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private V2ApplicationRepository applicationRepository;

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
        V2EventMessage v2EventMessage = DatabaseCache.EVENT_MESSAGE_MAP.get(eventCode);
        if (v2EventMessage == null) {
            throw new EventException("Application error", HttpStatus.NOT_FOUND.toString(), "/event/code/" + eventCode, "Event code details could not be retrieved.");
        }
        return new EventMessage(v2EventMessage.getCode(), v2EventMessage.getAction_message(),
                v2EventMessage.getBap_message(), v2EventMessage.getBpp_message());
    }

    public void reloadEventMessages() {
        List<V2EventMessage> v2EventMessages = eventMessageRepository.findAll();
        DatabaseCache.EVENT_MESSAGE_MAP.clear();
        for (V2EventMessage eventMessage : v2EventMessages) {
            DatabaseCache.EVENT_MESSAGE_MAP.put(eventMessage.getCode(), eventMessage);
        }

        List<V2Application> v2Applications = applicationRepository.findAll();
        DatabaseCache.APPLICATION_MAP.clear();
        for (V2Application application : v2Applications) {
            DatabaseCache.APPLICATION_MAP.put(application.getApp_id(), application);
        }
    }

    public V2Event GetEventDetails(int eventId) {
            Optional<V2Event> v2Event = eventRepository.findById(eventId);
            if(!v2Event.isPresent())
                throw new EventException("Application error", HttpStatus.NOT_FOUND.toString(), "/event/" + eventId, "Event details could not be retrieved.");
            return v2Event.get();
    }
}
