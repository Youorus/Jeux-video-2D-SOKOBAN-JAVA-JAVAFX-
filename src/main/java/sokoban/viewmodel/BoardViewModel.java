package sokoban.viewmodel;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board;
import sokoban.model.Grid;

public class BoardViewModel {

    private final GridViewModel grilleViewModel;
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
        grilleViewModel = new GridViewModel(board);
    }

    public GridViewModel getGridViewModel() {
        return grilleViewModel;
    }

    public int maxFilledCells() {
        return Board.maxFilledCells();
    }
    public LongBinding filledCellsCountProperty() {
        return board.filledCellsCountProperty();
    }

    public static int gridWidth() {
        return Grid.getGridWidth();
    }

    public static int gridHeight() {
        return Grid.getGridHeight();
    }




}
