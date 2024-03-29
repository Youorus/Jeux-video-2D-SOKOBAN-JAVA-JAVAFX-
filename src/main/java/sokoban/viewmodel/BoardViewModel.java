package sokoban.viewmodel;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board;
import sokoban.model.Cell;
import sokoban.model.ErrorBox;
import sokoban.model.Grid;

public class BoardViewModel {
//    private final IntegerBinding filledCellsCount;
//    private final BooleanExpression isGridHalfFilled;

    private final GridViewModel grilleViewModel;

    public ErrorBoxViewModel getErrorBoxViewModel() {
        return errorBoxViewModel;
    }

    public ToolsBoxViewModel getToolsBoxViewModel() {
        return toolsBoxViewModel;
    }

    private final ToolsBoxViewModel toolsBoxViewModel;

private final ErrorBoxViewModel errorBoxViewModel;
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
        grilleViewModel = new GridViewModel(board);
        this.toolsBoxViewModel = new ToolsBoxViewModel(board.getToolsBox());
        this.errorBoxViewModel = new ErrorBoxViewModel(board.getErrorBox());
    }
    public Cell[][] getMatrix(){
       return board.getMatrix();
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
