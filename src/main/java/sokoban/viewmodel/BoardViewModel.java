package sokoban.viewmodel;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board;

public class BoardViewModel {

    private final GrilleViewModel grilleViewModel;
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
        grilleViewModel = new GrilleViewModel(board);
    }

    public GrilleViewModel getGrilleViewModel() {
        return grilleViewModel;
    }

    public int maxFilledCells() {
        return Board.maxFilledCells();
    }
    public LongBinding filledCellsCountProperty() {
        return board.filledCellsCountProperty();
    }
}
