package com.beckn.eventsCollector.controller;


import com.beckn.eventsCollector.dto.EventDTO;
import com.beckn.eventsCollector.model.Event;
import com.beckn.eventsCollector.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/event")
    public ResponseEntity<EventDTO> saveOrUpdateEvent(@RequestBody EventDTO inputEvent) {
        EventDTO eventDTO = eventService.saveOrUpdateEvent(inputEvent);
        return ResponseEntity.ok(eventDTO);
    }
}
