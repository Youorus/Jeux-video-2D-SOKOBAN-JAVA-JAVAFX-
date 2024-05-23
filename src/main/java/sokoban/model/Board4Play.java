package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;

public class Board4Play extends Board{

    public MovePlayerCommand getMoveCommand() {
        return moveCommand;
    }

    private final  MovePlayerCommand moveCommand = new MovePlayerCommand(this);

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private final Grid4Play grid4Play;

    public IntegerProperty moveCountProperty() {
        return getGrid4Play().moveCountProperty();
    }

    // Méthode pour exécuter un déplacement et le stocker dans l'historique des commandes
    public void executeMove(Direction direction) {
        moveCommand.execute();
        movePlayer(direction);
        moveCommand.getStatesRedoStack().push(direction);
    }

    public void redoIfCellsChange(){
        moveCommand.execute();
    }

    // Méthode pour annuler le dernier déplacement
    public void undoMove() {
        if (!moveCommand.getStatesStack().isEmpty()){
            this.grid4Play.setMoveCount(this.grid4Play.getMoveCount() + 5);
        }
      moveCommand.undo();
    }

    public void redoMove() {
        moveCommand.redo();
    }
    public IntegerProperty boxAndGoalCountProperty() {
        return grid4Play.boxOnGoalCountProperty();
    }

    public Board4Play(Board4Design board4Design){
        grid4Play = new Grid4Play(this, board4Design.getGrid4Design());
        this.grid4Play.copyElements(board4Design.getGrid4Design());
        this.grid4Play.getCell4Play().addMushroomToRandomEmptyCell();
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


    public void movePlayer(Direction direction){
        switch (direction) {
            case UP:
                movePlayerUp();
                break;
            case DOWN:
                movePlayerDown();
                break;
            case LEFT:
               movePlayerLeft();
                break;
            case RIGHT:
                movePlayerRight();
                break;
        }
    }

    public int numberGoal() {
       return getGrid4Play().numberGoal();
    }
}
