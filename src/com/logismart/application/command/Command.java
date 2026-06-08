package com.logismart.application.command;

public interface Command {
    void execute();
    void undo();
}