package com.envoy.rater.events;

public class Msg {
    public enum EventType {
        NEW_GAME,
        UPDATE_GAMES
    }

    private EventType mEvent;

    public Msg(EventType type) {
        mEvent = type;
    }

    public EventType getEvent() {
        return mEvent;
    }

    public void setEvent(EventType mEvent) {
        this.mEvent = mEvent;
    }
}
