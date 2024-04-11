package sokoban.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

abstract public class Grid<T extends Cell> {

    public T[][] getMatrix() {
        return matrix;
    }


    public ObservableList<Element> getCellsElements(int line, int col) {
        return getMatrix()[line][col].getCellsElements();
    }

    private  T[][] matrix;





    public IntegerProperty gridHeightProperty() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight.set(gridHeight);
    }

    public int getGridHeight() {
        return gridHeight.get();
    }

    private IntegerProperty gridHeight = new SimpleIntegerProperty();

    public IntegerProperty gridWidthProperty() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth.set(gridWidth);
    }

    public int getGridWidth() {
        return gridWidth.get();
    }

    private IntegerProperty gridWidth = new SimpleIntegerProperty();

    public void reset(int height, int width){
        this.gridHeight.set(height);
        this.gridWidth.set(width);
        this.matrix = createMatrix(height, width);
        for (int i = 0; i < gridHeight.get(); ++i) {
            for (int j = 0; j < gridWidth.get(); ++j) {
                matrix[i][j] = createCell();
            }
        }
    }

    public int[] getPlayerPosition() {
        for (int i = 0; i < getGridHeight(); i++) {
            for (int j = 0; j < getGridWidth(); j++) {
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
