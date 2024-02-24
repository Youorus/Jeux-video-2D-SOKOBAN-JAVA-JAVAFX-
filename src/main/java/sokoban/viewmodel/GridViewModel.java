package sokoban.viewmodel;

import sokoban.model.Board;
import sokoban.model.Grid;

public class GridViewModel {
    private final Grid grid;

    public GridViewModel(Grid grid) {
        this.grid = grid;
    }

    public CellViewModel getCellViewModel(int line, int col) {
        return new CellViewModel(line, col, grid);
    }
}