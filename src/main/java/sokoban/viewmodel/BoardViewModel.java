package sokoban.viewmodel;

import sokoban.model.Grid4Design;

abstract public class BoardViewModel {

    public static int gridWidth() {
        return Grid4Design.getGridWidth();
    }

    public static int gridHeight() {
        return Grid4Design.getGridHeight();
    }
}
