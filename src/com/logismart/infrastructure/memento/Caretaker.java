package com.logismart.infrastructure.memento;
import java.util.Stack;

public class Caretaker {
    private Stack<EnvioMemento> history = new Stack<>();
    public void save(EnvioMemento m) { history.push(m); }
    public EnvioMemento undo() { return history.pop(); }
}