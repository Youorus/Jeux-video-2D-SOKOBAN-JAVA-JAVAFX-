package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import sokoban.model.Cell;
import sokoban.model.CellValue;

import java.util.Arrays;

public class Grid {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;
    //les rendre non static

    private final Cell[][] matrix;
    private final LongBinding filledCellsCount;

//    public Grid(){
//        initialiseMatrix(int heigh)
//    }

    public Grid() {
        matrix = new Cell[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Cell();
                matrix[i][j].setValue(CellValue.ground);
            }
        }

        filledCellsCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isEmpty())
                .count());
    }

    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static int getGridHeight() {
        return GRID_HEIGHT;
    }

    public ReadOnlyObjectProperty<CellValue> valueProperty(int line, int col) {
        return matrix[line][col].valueProperty();
    }

    public CellValue getValue(int line, int col) {
        return matrix[line][col].getValue();
    }

    public void play(int line, int col, CellValue playerValue) {
        matrix[line][col].setValue(playerValue);
        filledCellsCount.invalidate();
    }

    public LongBinding filledCellsCountProperty() {
        return filledCellsCount;
    }

    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }
}
