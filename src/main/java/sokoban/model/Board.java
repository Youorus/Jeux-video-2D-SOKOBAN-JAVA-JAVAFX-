package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import sokoban.view.BoardView;
import sokoban.view.ToolsBoxView;
import sokoban.viewmodel.ToolsBoxViewModel;

public class Board {

    private Ground ground = new Ground();
    private Wall wall = new Wall();
    private Player player = new Player();
    private Box box = new Box();
    private Goal goal = new Goal();
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
        System.out.println(ToolsBoxView.getElementObject());
        if (grid.getCellsElements(line, col).contains(wall)) {
            System.out.println("Impossible d'ajouter un mur ici");
        } else if (grid.getCellsElements(line, col).contains(player) && element.equals(box)) {
            System.out.println("Impossible de placer une caisse sur un joueur");
        } else if (grid.getCellsElements(line, col).contains(goal) && element.equals(goal)) {
            System.out.println("Impossible de placer une cible sur une autre cible");
        } else {
            grid.add(line, col, element);
        }
    }

}
