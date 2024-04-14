package sokoban.viewmodel;

abstract public class GridViewModel<T extends CellViewModel> {
    public abstract CellViewModel getCellViewModel(int line, int col);
}
