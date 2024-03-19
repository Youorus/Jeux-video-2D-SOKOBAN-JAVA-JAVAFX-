package sokoban.viewmodel;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board;
import sokoban.model.Grid;

public class BoardViewModel {
//    private final IntegerBinding filledCellsCount;
//    private final BooleanExpression isGridHalfFilled;

    private final GridViewModel grilleViewModel;

    private boolean hasPlayer;

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
