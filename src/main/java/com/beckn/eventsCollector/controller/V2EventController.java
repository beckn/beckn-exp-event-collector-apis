package com.beckn.eventsCollector.controller;


import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.service.V2EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class V2EventController {

    @Autowired
    private V2EventService eventService;

    @PostMapping(value = "/event")
    public ResponseEntity<?> saveOrUpdateEvent(@RequestBody V2EventDTO inputEvent) {
        if (inputEvent.getExperienceId() == null) {
            return new ResponseEntity<>("Experience id is missing.", HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getEventCode() == null) {
            return new ResponseEntity<>("Event code is missing.", HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getSourceAppId() == null) {
            return new ResponseEntity<>("Source app id is missing.", HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getDestinationAppId() == null) {
            return new ResponseEntity<>("Destination id is missing.", HttpStatus.BAD_REQUEST);
        }
        try {
            int eventId = eventService.saveOrUpdateEvent(inputEvent);
            return ResponseEntity.ok("Event Id : " + eventId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
