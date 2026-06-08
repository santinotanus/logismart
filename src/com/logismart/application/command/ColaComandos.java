package com.logismart.application.command;

import java.util.Stack;

public class ColaComandos {
    private Stack<Command> history = new Stack<>();

    public void ejecutar(Command cmd) {
        cmd.execute();
        history.push(cmd);
    }

    public void deshacer() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo();
        } else {
            System.out.println("[LogiSmart] No hay operaciones para deshacer.");
        }
    }
}