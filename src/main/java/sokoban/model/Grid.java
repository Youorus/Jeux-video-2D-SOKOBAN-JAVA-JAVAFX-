package sokoban.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Set;

abstract public class Grid<T extends Cell> {

    public T[][] getMatrix() {
        return matrix;
    }


    public ObservableList<Element> getCellsElements(int line, int col) {
        return getMatrix()[line][col].getCellsElements();
    }

    private final T[][] matrix;
    private final int GRID_HEIGHT;
    private final int GRID_WIDTH;

    public Grid(int height, int width){
        this.GRID_HEIGHT = height;
        this.GRID_WIDTH = width;
        this.matrix = createMatrix(GRID_HEIGHT, GRID_WIDTH);
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = createCell();
            }
        }
    }


    protected abstract T[][] createMatrix(int height, int width);


    public abstract T createCell();
}
