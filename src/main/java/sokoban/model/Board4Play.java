package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Board4Play extends Board{

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private Grid4Play grid4Play = new Grid4Play();

    public IntegerProperty moveCountProperty() {
        return getGrid4Play().moveCountProperty();
    }

    public IntegerProperty boxAndGoalCountProperty() {
        return grid4Play.boxOnGoalCountProperty();
    }

    public Board4Play(Board4Design board4Design){
        this.grid4Play.copyElements(board4Design.getGrid());
    }

    public void movePlayerUp(){
      grid4Play.movePlayerUp(getPlayer(), getWall(), getBox(), getGoal());
    }
    public void movePlayerDown(){
        grid4Play.movePlayerDown(getPlayer(), getWall(), getBox(), getGoal());
    }

    public void movePlayerRight(){
        grid4Play.movePlayerRight(getPlayer(), getWall(), getBox(), getGoal());
    }

    public void movePlayerLeft(){
        grid4Play.movePlayerLeft(getPlayer(), getWall(), getBox(), getGoal());
    }

    public SimpleBooleanProperty playerWinProperty() {
        return getGrid4Play().playerWinProperty();
    }

    public int getMoveCount() {
        return grid4Play.getMoveCount();
    }


    public int numberGoal() {
       return getGrid4Play().numberGoal();
    }
}
