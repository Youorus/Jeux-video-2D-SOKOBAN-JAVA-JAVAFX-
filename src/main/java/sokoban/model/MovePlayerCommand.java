package sokoban.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MovePlayerCommand implements Command{
    private final Board4Play board4Play;
    private final Stack<List<CellState>> statesStack = new Stack<>();
    private final Stack<Direction> statesRedoStack = new Stack<>();

    public Stack<Direction> getStatesRedoStack() {
        return statesRedoStack;
    }

    public MovePlayerCommand(Board4Play board4Play) {
        this.board4Play = board4Play;
    }

    @Override
    public void execute() {
        // Je dois faire une copie de l'état actuel des cellules et l'ajouter à la pile
        saveState(board4Play.getGrid4Play());
    }

    @Override
    public void undo() {
        // Restaure l'état précédent de la grille
        if (!statesStack.isEmpty()) {
            List<CellState> prevState = statesStack.pop();
            restoreState(prevState);
        }
    }

    @Override
    public void redo() {
       if (!statesRedoStack.isEmpty()){
           Direction redoDirection = statesRedoStack.pop();
           board4Play.movePlayer(redoDirection);
       }
    }

    private void saveState(Grid4Play grid4Play) {
        List<CellState> currentState = new ArrayList<>();

        // Parcourt toutes les cellules de la grille
        for (int i = 0; i < grid4Play.getGridHeight(); i++) {
            for (int j = 0; j < grid4Play.getGridWidth(); j++) {
                // Obtient les éléments de la cellule actuelle
                ObservableList<Element> elements = FXCollections.observableArrayList(grid4Play.getCellsElements(i, j));
                // Crée une copie des éléments
                ObservableList<Element> elementsCopy = FXCollections.observableArrayList(elements);
                // Crée un état de cellule et l'ajoute à la liste des états
                currentState.add(new CellState(grid4Play.getMatrix()[i][j], elementsCopy));
            }
        }

        // Ajoute l'état actuel à la pile
        statesStack.push(currentState);
    }

    private void restoreState(List<CellState> prevState) {
        // Restaure l'état précédent de la grille
        Grid4Play grid4Play = board4Play.getGrid4Play();
        for (int i = 0; i < grid4Play.getGridHeight(); i++) {
            for (int j = 0; j < grid4Play.getGridWidth(); j++) {
                Cell4Play cell = grid4Play.getMatrix()[i][j];
                CellState cellState = prevState.get(i * grid4Play.getGridWidth() + j);
                cell.getCellsElements().setAll(cellState.getElements());
            }
        }
    }

}
