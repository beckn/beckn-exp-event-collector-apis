package com.beckn.eventsCollector.mapper;

import com.beckn.eventsCollector.dto.EventContext;
import com.beckn.eventsCollector.dto.EventDTO;
import com.beckn.eventsCollector.dto.EventDestination;
import com.beckn.eventsCollector.dto.EventSource;
import com.beckn.eventsCollector.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventToEventDtoMapper {
    public EventDTO mapEventToEventDto(Event event) {
        EventDTO eventDto = new EventDTO();
        eventDto.setEventId(event.getEvent_id());
        eventDto.setExperienceId(event.getExperience_id());
        eventDto.setEventCode(event.getEvent_code());
        eventDto.setEventTitle(event.getEvent_title());
        eventDto.setEventMessage(event.getEvent_message());

        EventSource eventSource = new EventSource(event.getEvent_source_type(), event.getEvent_source_id());
        eventDto.setEventSource(eventSource);

        EventDestination eventDestination = new EventDestination(event.getEvent_destination_type(), event.getEvent_destination_id());
        eventDto.setEventDestination(eventDestination);

        eventDto.setPayload(event.getPayload());

        EventContext eventContext = new EventContext(event.getContext_transaction_id(), event.getContext_message_id());
        eventDto.setContext(eventContext);

        eventDto.setEventStart_ts(event.getEventStart_ts());
        eventDto.setEventEnd_ts(event.getEventEnd_ts());
        eventDto.setCreated_ts(event.getCreated_ts());
        eventDto.setLastModified_ts(event.getLastModified_ts());
        return eventDto;
    }
}
