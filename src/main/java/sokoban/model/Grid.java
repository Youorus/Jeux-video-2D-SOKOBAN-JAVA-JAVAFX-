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


    public int getGridHeight() {
        return gridHeight.get();
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight.set(gridHeight);
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth.set(gridWidth);
    }

    public IntegerProperty gridHeightProperty() {
        return gridHeight;
    }

    private final IntegerProperty gridHeight = new SimpleIntegerProperty(15);



    public IntegerProperty gridWidthProperty() {
        return gridWidth;
    }

    public int getGrid4PlayHeight() {
        return grid4PlayHeight.get();
    }

    public IntegerProperty grid4PlayHeightProperty() {
        return grid4PlayHeight;
    }

    public int getGrid4PLayWidth() {
        return grid4PLayWidth.get();
    }

    public IntegerProperty grid4PLayWidthProperty() {
        return grid4PLayWidth;
    }

    private final IntegerProperty grid4PlayHeight = new SimpleIntegerProperty();
    private final IntegerProperty grid4PLayWidth = new SimpleIntegerProperty();


    public int getGridWidth() {
        return gridWidth.get();
    }

    private final IntegerProperty gridWidth = new SimpleIntegerProperty(10);


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
                if (getMatrix()[i][j].getCellsElements().contains(new Player() )) {
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
