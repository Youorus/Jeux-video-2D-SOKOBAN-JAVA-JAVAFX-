package sokoban.viewmodel;

import sokoban.model.Board4Design;
import sokoban.model.Board4Play;

public class Grid4PlayViewModel {

    private final Board4Play board4Play;

    Grid4PlayViewModel(Board4Play board4Play){
        this.board4Play = board4Play;
    }

    public Cell4PlayViewModel getCellViewModel(int line, int col) {
        return new Cell4PlayViewModel(line, col, board4Play);
    }
}
