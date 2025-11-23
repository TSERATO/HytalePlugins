package com.fancyinnovations.fancycore.api.events;

public abstract class FancyEvent {

    private final long firedAt;
    private boolean cancelled;

    public FancyEvent() {
        this.firedAt = System.currentTimeMillis();
        this.cancelled = false;
    }

    public void fire()  {

    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public long firedAt() {
        return firedAt;
    }
}
