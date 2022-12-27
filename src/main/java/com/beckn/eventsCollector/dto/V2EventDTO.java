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
public class V2EventDTO {
    private String experienceId;
    private String eventCode;
    private String action;
    private String sourceAppId;
    private String destinationAppId;
    private Date eventStart_ts;
    private String payload;
}
