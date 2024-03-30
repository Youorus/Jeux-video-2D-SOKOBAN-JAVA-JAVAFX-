package sokoban.model;

import javafx.beans.binding.Bindings;

import java.util.Arrays;

public class Grid4Play {

    private final Cell4Play[][] matrix;
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;

    public Grid4Play() {
        matrix = new Cell4Play[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Cell4Play();// je construis une nouvelle cell
                matrix[i][j].setValue(new Ground()); // Initialisation avec Ground
            }
        }
    }
}
