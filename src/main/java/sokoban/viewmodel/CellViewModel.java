package sokoban.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import sokoban.model.Cell;
import sokoban.model.Element;

import java.util.HashSet;

abstract public class CellViewModel {

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    private final int line, col;

    public CellViewModel(int line, int col) {
        this.line = line;
        this.col = col;
    }
}

