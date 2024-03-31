package sokoban.viewmodel;

import sokoban.model.Board4Design;

public class Grid4DesignViewModel extends GridViewModel<Cell4DesignViewModel>{

    private final Board4Design board4Design;
    

    Grid4DesignViewModel(Board4Design board4Design) {
        this.board4Design = board4Design;
    }

    public Cell4DesignViewModel getCellViewModel(int line, int col) {
        return new Cell4DesignViewModel(line, col, board4Design);
    }

}