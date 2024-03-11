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
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
//        this.filledCellsCount = board.filledCellsCountProperty().asInteger();
//        this.isGridHalfFilled = filledCellsCount.greaterThanOrEqualTo(Board.maxFilledCells() / 2);

        grilleViewModel = new GridViewModel(board);
    }

    public GridViewModel getGridViewModel() {
        return grilleViewModel;
    }

//    public IntegerBinding filledCellsCountProperty() {
//        return filledCellsCount;
//    }
//
//    public BooleanExpression isGridHalfFilledProperty() {
//        return isGridHalfFilled;
//    }

    public int maxFilledCells() {
        return Board.maxFilledCells();
    }
    public LongBinding filledCellsCountProperty() {
        return board.filledCellsCountProperty();
    }

//    public void addElement() {
//        gridViewModel.addElement();
//        board.incrementFilledCellsCount();
//    }
//
//    public void removeElement() {
//        gridViewModel.removeElement();
//        board.decrementFilledCellsCount();
//    }

    public static int gridWidth() {
        return Grid.getGridWidth();
    }

    public static int gridHeight() {
        return Grid.getGridHeight();
    }




}
