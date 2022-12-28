package com.beckn.eventsCollector.controller;


import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.exception.EventException;
import com.beckn.eventsCollector.service.V2EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (inputEvent.getEventSourceId() == null) {
            return new ResponseEntity<>("Source app id is missing.", HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getEventDestinationId() == null) {
            return new ResponseEntity<>("Destination id is missing.", HttpStatus.BAD_REQUEST);
        }
        try {
            int eventId = eventService.saveOrUpdateEvent(inputEvent);
            return ResponseEntity.ok("Event Id : " + eventId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/event/experience")
    public ResponseEntity<String> getLatestExperienceSession() {
        try {
            String experienceId = eventService.getLatestExperienceSession();
            return ResponseEntity.ok("Experience Id : " + experienceId);
        } catch (EventException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
