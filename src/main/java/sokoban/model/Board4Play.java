package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Board4Play extends Board{

    private final CommandHistory commandHistory = new CommandHistory();

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private final Grid4Play grid4Play = new Grid4Play(this);

    public IntegerProperty moveCountProperty() {
        return getGrid4Play().moveCountProperty();
    }

    // Méthode pour exécuter un déplacement et le stocker dans l'historique des commandes
    public void executeMove(Direction direction) {
        MovePlayerCommand moveCommand = new MovePlayerCommand(this, direction);
        moveCommand.execute();
        commandHistory.push(moveCommand);
    }

    // Méthode pour annuler le dernier déplacement
    public void undoMove() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }

//    public void redoMove() {
//        if (commandHistory.hasRedoCommands()) {
//            Command nextCommand = commandHistory.peekRedoCommand();
//            nextCommand.redo();
//        }
//    }

    public IntegerProperty boxAndGoalCountProperty() {
        return grid4Play.boxOnGoalCountProperty();
    }

    public Board4Play(Board4Design board4Design){
        this.grid4Play.copyElements(board4Design.getGrid4Design());
    }

    public void movePlayerUp(){
        grid4Play.getCell4Play().movePlayerUp(getPlayer(), getWall(), getBox(), getGoal());
    }
    public void movePlayerDown(){
        grid4Play.getCell4Play().movePlayerDown(getPlayer(), getWall(), getBox(), getGoal());
    }

    public void movePlayerRight(){
        grid4Play.getCell4Play().movePlayerRight(getPlayer(), getWall(), getBox(), getGoal());
    }

    public void movePlayerLeft(){
        grid4Play.getCell4Play().movePlayerLeft(getPlayer(), getWall(), getBox(), getGoal());
    }

    public SimpleBooleanProperty playerWinProperty() {
        return getGrid4Play().playerWinProperty();
    }


    public int numberGoal() {
       return getGrid4Play().numberGoal();
    }
}
