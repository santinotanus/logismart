package com.logismart.application.mediator;

public interface Mediator {
    void notify(Object sender, String event, Object data);
}