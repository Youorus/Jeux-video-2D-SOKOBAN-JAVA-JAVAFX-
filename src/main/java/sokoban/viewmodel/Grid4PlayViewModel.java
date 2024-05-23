package sokoban.viewmodel;

import javafx.beans.property.IntegerProperty;
import sokoban.model.Board4Design;
import sokoban.model.Board4Play;

public class Grid4PlayViewModel extends GridViewModel<Cell4PlayViewModel> {

    public Board4Play getBoard4Play() {
        return board4Play;
    }

    private final Board4Play board4Play;

    Grid4PlayViewModel(Board4Play board4Play){
        this.board4Play = board4Play;
    }

    public void updateBoxOnGoalCount() {
        board4Play.getGrid4Play().updateBoxOnGoalCount();
    }

    public Cell4PlayViewModel getCellViewModel(int line, int col) {
        return new Cell4PlayViewModel(line, col, board4Play);
    }

}
