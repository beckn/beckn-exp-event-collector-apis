package com.beckn.eventsCollector.cache;

import com.beckn.eventsCollector.model.V2Application;
import com.beckn.eventsCollector.model.V2EventMessage;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCache {
    public static final Map<String, V2EventMessage> EVENT_MESSAGE_MAP = new HashMap<>();
    public static final Map<String, V2Application> APPLICATION_MAP = new HashMap<>();
}
