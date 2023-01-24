package com.beckn.eventsCollector.controller;


import com.beckn.eventsCollector.cache.DatabaseCache;
import com.beckn.eventsCollector.dto.V2EventDTO;
import com.beckn.eventsCollector.exception.EventControllerException;
import com.beckn.eventsCollector.exception.EventException;
import com.beckn.eventsCollector.model.EventMessage;
import com.beckn.eventsCollector.model.V2Event;
import com.beckn.eventsCollector.service.V2EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v2")
public class V2EventController {

    @Autowired
    private V2EventService eventService;

    @PostMapping(value = "/event")
    public ResponseEntity<?> saveOrUpdateEvent(@RequestBody V2EventDTO inputEvent) {
        if (inputEvent.getExperienceId() == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Experience id is missing."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getEventCode() == null || DatabaseCache.EVENT_MESSAGE_MAP.get(inputEvent.getEventCode()) == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Event code is missing or not found in store."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getEventSourceId() == null || DatabaseCache.APPLICATION_MAP.get(inputEvent.getEventSourceId()) == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Source app id is missing or not found in store."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        if (inputEvent.getEventDestinationId() == null || DatabaseCache.APPLICATION_MAP.get(inputEvent.getEventDestinationId()) == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Destination app id is missing or not found in store."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        try {
            int eventId = eventService.saveOrUpdateEvent(inputEvent);
            return ResponseEntity.ok(eventId);
        } catch (Exception e) {
            EventControllerException eventControllerException = new EventControllerException(
                    "System error", HttpStatus.INTERNAL_SERVER_ERROR.toString(), "/event", "Error processing request " + e.getMessage()
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/event/experience")
    public ResponseEntity<?> getLatestExperienceSession() {
        try {
            String experienceId = eventService.getLatestExperienceSession();
            return ResponseEntity.ok(experienceId);
        } catch (EventException e) {
            EventControllerException eventControllerException = new EventControllerException(
                    e.getType(), e.getCode(), e.getPath(), e.getMessage()
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/event/code/{eventCode}")
    public ResponseEntity<?> GetEventCodeDetails(@PathVariable String eventCode) {
        if (eventCode == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Event code is missing."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        try {
            EventMessage eventMessage = eventService.GetEventCodeDetails(eventCode);
            return ResponseEntity.ok(eventMessage);
        } catch (EventException e) {
            EventControllerException eventControllerException = new EventControllerException(
                    e.getType(), e.getCode(), e.getPath(), e.getMessage()
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/event/{eventId}")
    public ResponseEntity<?> GetEventDetails(@PathVariable Integer eventId) {
        if (eventId == null) {
            EventControllerException eventControllerException = new EventControllerException(
                    "Application error", HttpStatus.BAD_REQUEST.toString(), "/event", "Event code is missing."
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.BAD_REQUEST);
        }
        try {
            V2Event event = eventService.GetEventDetails(eventId);
            return ResponseEntity.ok(event);
        } catch (EventException e) {
            EventControllerException eventControllerException = new EventControllerException(
                    e.getType(), e.getCode(), e.getPath(), e.getMessage()
            );
            return new ResponseEntity<>(eventControllerException, HttpStatus.NOT_FOUND);
        }
    }

    @ApiIgnore
    @GetMapping(value = "/event/event-message")
    public ResponseEntity<?> reloadEventMessages() {
        eventService.reloadEventMessages();
        return ResponseEntity.ok("");
    }
}
