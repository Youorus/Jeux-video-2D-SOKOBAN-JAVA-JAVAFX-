package sokoban.viewmodel;

import sokoban.model.Board4Design;
import sokoban.model.Board4Play;

public class Cell4PlayViewModel extends CellViewModel {

    private final int line, col;
    private final Board4Play board4Play;

    Cell4PlayViewModel(int line, int col, Board4Play board4Play){
        this.line = line;
        this.col = col;
        this.board4Play = board4Play;
    }
}
