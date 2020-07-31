package com.endava.steps.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StepContext {

    private static final Logger log = LoggerFactory.getLogger(StepContext.class);
    private static Map<ContextKeys, Object> context = new HashMap<>();

    public Object getContext(ContextKeys key) {
        return context.get(key);
    }

    public void setContext(ContextKeys key, Object object) {
        if (context.get(key) != object) {
            context.put(key, object);
            log.debug("Context has been updated with the following key: {}", key);
        } else log.debug("Could not set context, the object for {} is already present in context...", key);
    }

    public void reset() {
        context.clear();
    }

}
