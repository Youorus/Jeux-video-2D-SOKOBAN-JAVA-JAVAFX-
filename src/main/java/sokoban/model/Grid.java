package sokoban.model;

abstract public class Grid<T extends Cell> {

    public T[][] getMatrix() {
        return matrix;
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
                matrix[i][j].setValue(new Ground());
            }
        }
    }
    protected abstract T[][] createMatrix(int height, int width);


    public abstract T createCell();
}
