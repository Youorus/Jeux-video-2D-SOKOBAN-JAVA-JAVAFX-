package sokoban.model;

import javafx.collections.ObservableList;

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

    public int[] getPlayerPosition() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Player())) {
                    return new int[]{i, j}; // Retourne les coordonnées du joueur
                }
            }
        }
        // Si aucun joueur n'est trouvé, retourne un tableau {-1, -1}
        return new int[]{-1, -1};
    }

    protected abstract void remove(int line, int col, Element element);

    protected abstract void add(int line, int col, Element element);



    protected abstract T[][] createMatrix(int height, int width);


    public abstract T createCell();
}
