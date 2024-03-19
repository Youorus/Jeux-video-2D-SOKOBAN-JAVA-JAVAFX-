package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import sokoban.view.BoardView;
import sokoban.view.BoiteAOutilsView;
import sokoban.viewmodel.BoardViewModel;

public class Board {

    private Ground ground = new Ground();
    static final int MAX_FILLED_CELLS = (Grid.getGridWidth() * Grid.getGridHeight()) / 2;

    public Grid getGrid() {
        return grid;
    }

    private final Grid grid = new Grid();

    private final BooleanBinding isComplete;
    private BoardView boardView;

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

    public Element add(int line, int col) {
        Element currentElement = grid.getValue(line, col); // Récupère l'élément actuel dans la grille
        Element newElement;

        // Vérifie si l'élément actuel est égal à 'ground'
        if (currentElement == ground) {
            newElement = BoiteAOutilsView.getElementObject();
        } else {
            newElement = ground; // Sinon, remplace par 'ground'
        }


        // Joue le nouvel élément dans la grille
        grid.add(line, col, newElement);

        // Met à jour l'image immédiatement après avoir joué l'élément

        return newElement;
    }
}
