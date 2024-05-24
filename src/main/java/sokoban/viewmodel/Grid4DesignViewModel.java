package sokoban.viewmodel;

import javafx.collections.ObservableList;
import sokoban.model.Board4Design;
import sokoban.model.Element;

public class Grid4DesignViewModel extends GridViewModel<Cell4DesignViewModel>{

    public Board4Design getBoard4Design() {
        return board4Design;
    }

    private final Board4Design board4Design;
    

    Grid4DesignViewModel(Board4Design board4Design) {
        this.board4Design = board4Design;
    }

    public Cell4DesignViewModel getCellViewModel(int line, int col) {
        return new Cell4DesignViewModel(line, col, board4Design);
    }


}