package sokoban.viewmodel;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board;

public class BoardViewModel {

    private final GridViewModel grilleViewModel;
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
        grilleViewModel = new GridViewModel(board);
    }

    public GridViewModel getGrilleViewModel() {
        return grilleViewModel;
    }

    public int maxFilledCells() {
        return Board.maxFilledCells();
    }
    public LongBinding filledCellsCountProperty() {
        return board.filledCellsCountProperty();
    }
}
