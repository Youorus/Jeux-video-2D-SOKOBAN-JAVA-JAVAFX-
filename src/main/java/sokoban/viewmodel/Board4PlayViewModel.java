package sokoban.viewmodel;

import sokoban.model.Board;
import sokoban.model.Board4Play;
import sokoban.model.Grid4Design;

public class Board4PlayViewModel {

    public Grid4PlayViewModel getGrid4PlayViewModel() {
        return grid4PlayViewModel;
    }

    private final Grid4PlayViewModel grid4PlayViewModel;
    private final Board4Play board4Play;

    public Board4PlayViewModel(Board4Play board4Play){
        this.board4Play = board4Play;
        grid4PlayViewModel = new Grid4PlayViewModel(board4Play);
    }

    public static int gridWidth() {
        return Grid4Design.getGridWidth();
    }

    public static int gridHeight() {
        return Grid4Design.getGridHeight();
    }
}
