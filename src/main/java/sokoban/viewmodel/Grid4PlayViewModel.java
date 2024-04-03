package sokoban.viewmodel;

import sokoban.model.Board4Design;
import sokoban.model.Board4Play;

public class Grid4PlayViewModel extends GridViewModel<Cell4PlayViewModel> {

    public Board4Play getBoard4Play() {
        return board4Play;
    }

    public Board4Design getBoard4Design() {
        return board4Design;
    }

    private Board4Design board4Design;

    private final Board4Play board4Play;

    Grid4PlayViewModel(Board4Play board4Play){
        this.board4Play = board4Play;
        this.board4Design = new Board4Design();
    }

    public Cell4PlayViewModel getCellViewModel(int line, int col) {
        return new Cell4PlayViewModel(line, col, board4Play);
    }
}
