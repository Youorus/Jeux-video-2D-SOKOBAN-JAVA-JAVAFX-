package sokoban.viewmodel;

abstract public class GridViewModel {
    public abstract CellViewModel getCellViewModel(int line, int col);
}
