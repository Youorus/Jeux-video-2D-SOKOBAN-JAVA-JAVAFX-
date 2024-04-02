package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.util.Arrays;

import static sokoban.model.Grid4Design.getGridHeight;
import static sokoban.model.Grid4Design.getGridWidth;

public class Grid4Play extends Grid<Cell4Play>  {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;

    public Grid4Play() {
        super(GRID_HEIGHT, GRID_WIDTH);
    }


    @Override
    protected Cell4Play[][] createMatrix(int height, int width) {
        return new Cell4Play[GRID_HEIGHT][GRID_WIDTH];
    }
    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
        return getMatrix()[line][col].valueProperty();
    }

    public void add(int line, int col, Element element) {
        getMatrix()[line][col].setValue(element);
        getMatrix()[line][col].add(element);
    }
    @Override
    public Cell4Play createCell() {
        return new Cell4Play();
    }
}
