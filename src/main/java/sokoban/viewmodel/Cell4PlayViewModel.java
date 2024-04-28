package sokoban.viewmodel;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import sokoban.model.*;

import java.util.List;

public class Cell4PlayViewModel extends CellViewModel {

    public Board4Play getBoard4Play() {
        return board4Play;
    }

    private final Board4Play board4Play;


    Cell4PlayViewModel(int line, int col, Board4Play board4Play){
       super(line, col);
        this.board4Play = board4Play;
    }

    public int  numberBox() {
        return board4Play.numberGoal();
    }

    public SimpleBooleanProperty playerWinProperty() {
        return board4Play.playerWinProperty();
    }

    public void movePlayerUp(){
       board4Play.movePlayerUp();

    }
    public void movePlayerDown(){
        board4Play.movePlayerDown();

    }
    public void movePlayerRight(){
        board4Play.movePlayerRight();
    }

    public void movePlayerLeft(){
        board4Play.movePlayerLeft();
    }

    public ObservableList<Element> getCellsElements() {
        return board4Play.getGrid4Play().getCellsElements(getLine(),getCol());
    }

}
