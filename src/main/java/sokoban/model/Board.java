package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import sokoban.view.BoardView;
import sokoban.view.ToolsBoxView;
import sokoban.viewmodel.ToolsBoxViewModel;

public class Board {

    private Ground ground = new Ground();
    static final int MAX_FILLED_CELLS = (Grid.getGridWidth() * Grid.getGridHeight()) / 2;

    public Grid getGrid() {
        return grid;
    }

    private final Grid grid = new Grid();

    private final BooleanBinding isComplete;

    public Board() { // Ajout du constructeur prenant BoardView comme paramètre
        isComplete = grid.filledCellsCountProperty().isEqualTo(MAX_FILLED_CELLS);
    }

    public static int maxFilledCells() {
        return MAX_FILLED_CELLS;
    }

    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
        return grid.valueProperty(line, col);
    }

    public LongBinding filledCellsCountProperty() {
        return grid.filledCellsCountProperty();
    }

    public boolean isComplete () {
        return isComplete.get();
    }

    public boolean isEmpty(int line, int col) {
        return grid.isEmpty(line, col);
    }

    public void add(int line, int col) {
        Element element = ToolsBoxView.getElementObject();

        //c'est ici que je dois faire la verification de mon board
        grid.add(line, col, element);
    }
}
