package com.beckn.eventsCollector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class EventDestination {
    private String eventDestinationType;
    private String eventDestinationId;
}
