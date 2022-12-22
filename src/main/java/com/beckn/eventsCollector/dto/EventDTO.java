package com.beckn.eventsCollector.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private int eventId;
    private String experienceId;
    private String eventCode;
    private String eventTitle;
    private String eventMessage;
    private EventSource eventSource;
    private EventDestination eventDestination;
    private EventContext context;
    private String payload;
    private Date eventStart_ts;
    private Date eventEnd_ts;
    private Date created_ts;
    private Date lastModified_ts;
}
