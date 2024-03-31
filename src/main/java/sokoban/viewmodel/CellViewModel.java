package sokoban.viewmodel;

abstract public class CellViewModel {
    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    private final int line, col;

    public CellViewModel(int line, int col) {
        this.line = line;
        this.col = col;
    }
}

