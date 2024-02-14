package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;

import java.util.Arrays;

public class Grille {

    private final Case[][] matrix;
    private static int width = 15;
    private static int height = 10;
    private final LongBinding filledCellsCount;

    static final int GRID_WIDTH = width*height;
    static final int MAX_FILLED_CELLS = GRID_WIDTH /2;



    public Grille(){
        //code à modifié
        matrix = new Case[GRID_WIDTH][];
        for (int i = 0; i < GRID_WIDTH; ++i) {
            matrix[i] = new Case[GRID_WIDTH];
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Case();
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


    public LongBinding filledCellsCountProperty() {
        return filledCellsCount;
    }

    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }

}
