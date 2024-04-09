package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.util.List;

public class Board4Play extends Board{

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private Grid4Play grid4Play = new Grid4Play();

    public IntegerProperty moveCountProperty() {
        return getGrid4Play().moveCountProperty();
    }

    public Board4Play(Board4Design board4Design){
        this.grid4Play.copyElements(board4Design.getGrid());
    }

    public void movePlayerUp(){
      grid4Play.movePlayerUp(getPlayer(), getWall(), getBox());
    }
    public void movePlayerDown(){
        grid4Play.movePlayerDown(getPlayer(), getWall(), getBox());
    }

    public void movePlayerRight(){
        grid4Play.movePlayerRight(getPlayer(), getWall(), getBox());
    }

    public void movePlayerLeft(){
        grid4Play.movePlayerLeft(getPlayer(), getWall(), getBox());
    }



    public int numberGoal() {
       return getGrid4Play().numberGoal();
    }
}
