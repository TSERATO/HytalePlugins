package com.fancyinnovations.fancycore.api.events;

public interface EventListener<T extends FancyEvent> {

    void on(T event);

}
