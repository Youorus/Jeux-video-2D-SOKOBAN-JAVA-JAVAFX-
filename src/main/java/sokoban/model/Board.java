package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;

public class Board {

    static final int MAX_FILLED_CELLS = (Grid.getGridWidth() * Grid.getGridHeight()) / 2;

    private final Grid grid = new Grid();

    private final BooleanBinding isComplete;

    public Board(){
        isComplete = grid.filledCellsCountProperty().isEqualTo(Board.MAX_FILLED_CELLS);
    }

    public static int maxFilledCells() {
        return MAX_FILLED_CELLS;
    }

    public ReadOnlyObjectProperty<CellValue> valueProperty(int line, int col) {
        return grid.valueProperty(line, col);
    }

    public LongBinding filledCellsCountProperty() {
        return grid.filledCellsCountProperty();
    }

    public boolean isComplete () {
        return isComplete.get();
    }

    public boolean isEmpty(int line, int col) {
        return grid.isEmpty(line, col);
    }



}
