package com.beckn.eventsCollector.mapper;

import com.beckn.eventsCollector.dto.EventDTO;
import com.beckn.eventsCollector.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventDtoToEventMapper {
    public Event mapEventDtoToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setExperience_id(eventDTO.getExperienceId());
        event.setEvent_code(eventDTO.getEventCode());
        event.setEvent_title(eventDTO.getEventTitle());
        event.setEvent_message(eventDTO.getEventMessage());
        event.setEvent_layer(eventDTO.getEventLayer().toString());
        event.setEvent_source_type(eventDTO.getEventSourceType());
        event.setEvent_destination_type(eventDTO.getEventDestinationType());
        event.setEvent_source_id(eventDTO.getEventSourceId());
        event.setEvent_destination_id(eventDTO.getEventDestinationId());
        event.setPayload(eventDTO.getPayload());
        event.setContext_transaction_id(eventDTO.getContext().getTransactionId());
        event.setContext_message_id(eventDTO.getContext().getMessageId());
        event.setEventStart_ts(eventDTO.getEventStart_ts());
        event.setEventEnd_ts(eventDTO.getEventEnd_ts());
        event.setCreated_ts(eventDTO.getCreated_ts());
        event.setLastModified_ts(eventDTO.getLastModified_ts());
        return event;
    }
}
