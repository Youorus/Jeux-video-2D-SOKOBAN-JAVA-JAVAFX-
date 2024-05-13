package sokoban.model;

import java.util.Stack;

public class CommandHistory {

    private final Stack<Command> historyMove = new Stack<>();
    private final Stack<Command> redoCommands = new Stack<>();

    public void push(Command c) {
        historyMove.push(c);
    }

    public Command pop() {
        return historyMove.pop();
    }

    public boolean isEmpty() {
        return historyMove.isEmpty();
    }

    // Méthodes pour gérer les commandes redo
    public boolean hasRedoCommands() {
        return !redoCommands.isEmpty();
    }

    public Command peekRedoCommand() {
        return redoCommands.peek();
    }

    public void pushRedo(Command c) {
        redoCommands.push(c);
    }

    public Command popRedo() {
        return redoCommands.pop();
    }

    public void clearRedoCommands() {
        redoCommands.clear();
    }
}
