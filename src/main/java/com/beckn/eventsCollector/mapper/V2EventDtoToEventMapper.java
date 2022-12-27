package com.beckn.eventsCollector.mapper;

import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.model.V2Event;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class V2EventDtoToEventMapper {
    public V2Event mapEventDtoToEvent(V2EventDTO eventDTO) {
        V2Event event = new V2Event();
        event.setExperience_id(eventDTO.getExperienceId());
        event.setEvent_code(eventDTO.getEventCode());
        event.setAction(eventDTO.getAction());
        event.setSource_app_id(eventDTO.getSourceAppId());
        event.setDestination_app_id(eventDTO.getDestinationAppId());
        event.setPayload(eventDTO.getPayload());
        event.setStart(eventDTO.getEventStart_ts());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date date = new Date();
        try {
            event.setCreated_at(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(formatter.format(date)));
            event.setLast_modified_at(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(formatter.format(date)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return event;
    }
}
