package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Board4Play extends Board{

    public CommandStack getCommandStack() {
        return commandStack;
    }

    private final CommandStack commandStack = new CommandStack(this);

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private final Grid4Play grid4Play;

    public IntegerProperty moveCountProperty() {
        return getGrid4Play().moveCountProperty();
    }

    // Méthode pour exécuter un déplacement et le stocker dans l'historique des commandes
    public void execute(Direction direction) {
        commandStack.execute();
        movePlayer(direction);
        commandStack.getStatesRedoStack().push(direction);
    }

    public void undoIfCellsChange(){
        commandStack.execute();
    }

    // Méthode pour annuler le dernier déplacement
    public void undo() {
        if (!commandStack.getStatesStack().isEmpty()){
            this.grid4Play.setMoveCount(this.grid4Play.getMoveCount() + 5);
        }
      commandStack.undo();
    }

    public void redo() {
        commandStack.redo();
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
