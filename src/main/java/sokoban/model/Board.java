package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;

public class Board {

    static final int MAX_FILLED_CELLS = 10;
    //pas 10 mais la moitié des case existante : (width * height) /2 de la grille
    private final Grid grid = new Grid();
    //pour le compteur
    //private final BooleanBinding isComplete;

    public Board(){

    }

    public static int maxFilledCells() {
        return MAX_FILLED_CELLS;
    }
    public LongBinding filledCellsCountProperty() {
        return grid.filledCellsCountProperty();
    }




}
