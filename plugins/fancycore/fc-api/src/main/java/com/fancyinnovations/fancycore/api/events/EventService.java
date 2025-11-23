package com.fancyinnovations.fancycore.api.events;

public interface EventService {

    boolean fireEvent(FancyEvent event);

    void registerListener(Class<? extends FancyEvent> event, EventListener<?> listener);

}
