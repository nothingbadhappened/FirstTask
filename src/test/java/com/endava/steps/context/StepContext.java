package com.endava.steps.context;

import com.endava.helpers.util.actionsUtil.PageFactory;
import com.endava.pageObjects.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StepContext {

    private static final Logger log = LoggerFactory.getLogger(StepContext.class);
    private static Map<ContextKeys, Object> context = new HashMap<>();

    @Autowired
    PageFactory pageFactory;

    public Object getContext(ContextKeys key) {
        //Filter PAGE context
        if (key.getType().equals("PAGE") || key.getType().equals("CURRENT PAGE")) {
            //Create PAGE if not yet present in the context
            if (context.get(key) == null) {
                Page page = pageFactory.getPage(key);
                context.put(key, page);
                return page;
            } else //Pull existing PAGE
                return context.get(key);
        } else // Filter USER context
            if (key.getType().equals("USER") || key.getType().equals("CURRENT_USER")) {
            return context.get(key);
        } else throw new IllegalArgumentException("Cannot get context. Bad argument: " + key);

    }

    public void setContext(ContextKeys key, Object object) {
        // Always set/overwrite CURRENT page/user object and set only those objects that have not been created yet
        if (key.getType().equals("CURRENT PAGE") || key.getType().equals("CURRENT USER") || context.get(key) != object) {
            context.put(key, object);
            log.debug("Context has been updated with the following key: {}", key);
        } else log.debug("Could not set context, the object for {} is already present in the context...", key);
    }

    public void resetContext() {
        context.clear();
    }


}
