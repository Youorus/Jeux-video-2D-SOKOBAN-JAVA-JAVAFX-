package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;

    public Set<Element> getGridArrays() {
        return gridArrays;
    }

    //les rendre non static
    private Set<Element> gridArrays = new HashSet<>();
    private final Cell[][] matrix;
    private final LongBinding filledCellsCount;

    public Grid() {
        matrix = new Cell[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Cell();// je construis une nouvelle cell
                matrix[i][j].setValue(new Ground()); // Initialisation avec Ground
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

    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
        return matrix[line][col].valueProperty();
    }


    public void add(int line, int col, Element element) {
        matrix[line][col].setValue(element);
        matrix[line][col].add(element);
        System.out.println(matrix[line][col].getCellsElements());
        gridArrays.add(element);
        filledCellsCount.invalidate();
    }

    public Set<Element> getCellsElements(int line, int col) {
        return matrix[line][col].getCellsElements();
    }




    public LongBinding filledCellsCountProperty() {
        return filledCellsCount;
    }


    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }

    public Element getValue(int line, int col) {
        return matrix[line][col].getValue();
    }
}
