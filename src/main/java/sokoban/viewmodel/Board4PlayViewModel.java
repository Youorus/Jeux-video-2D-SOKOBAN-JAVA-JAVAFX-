package sokoban.viewmodel;

import javafx.beans.property.IntegerProperty;
import sokoban.model.Board;
import sokoban.model.Board4Play;
import sokoban.model.Grid4Design;

public class Board4PlayViewModel extends BoardViewModel {

    public Grid4PlayViewModel getGrid4PlayViewModel() {
        return grid4PlayViewModel;
    }

    private final Grid4PlayViewModel grid4PlayViewModel;
    private final Board4Play board4Play;

    public Board4PlayViewModel(Board4Play board4Play){
        this.board4Play = board4Play;
        grid4PlayViewModel = new Grid4PlayViewModel(board4Play);
    }

    public IntegerProperty moveCountProperty() {
        return board4Play.moveCountProperty();
    }

    public IntegerProperty boxAndGoalCountProperty() {
        return board4Play.boxAndGoalCountProperty();
    }

}
