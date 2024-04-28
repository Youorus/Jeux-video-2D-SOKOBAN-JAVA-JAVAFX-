package sokoban.model;

import java.util.Stack;

public class CommandHistory {

    private final Stack<Command> historyMove = new Stack<>();

    public void push(Command c) {
        historyMove.push(c);
    }

    public Command pop() {
        return historyMove.pop();
    }

    public boolean isEmpty() { return historyMove.isEmpty(); }
}
