package sokoban.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovePlayerCommand implements Command{
    private final Board4Play board4Play;

    public List<CellState> getCellStates() {
        return cellStates;
    }

    private final List<CellState> cellStates = new ArrayList<>();

    public MovePlayerCommand(Board4Play board4Play){
        this.board4Play = board4Play;
    }



    @Override
    public void execute() {
        // je dois faire une copie de l'etat actuelle de mes cells
        saveState(board4Play.getGrid4Play());
    }

    @Override
    public void undo() {
        // restore l'etat precedent
        copyElementsToGrid();
    }

    @Override
    public void redo() {
        //rexecute l'etat comme il etait
        execute();
    }

    private void saveState(Grid4Play grid4Play) {
        cellStates.clear();

        // Parcourir toutes les cellules de la grille
        for (int i = 0; i < grid4Play.getGridHeight(); i++) {
            for (int j = 0; j < grid4Play.getGridWidth(); j++) {
                // Obtenir les éléments de la cellule actuelle
                ObservableList<Element> elements = FXCollections.observableArrayList(grid4Play.getCellsElements(i, j));

                // Créer une copie des éléments
                ObservableList<Element> elementsCopy = FXCollections.observableArrayList(elements);

                // Créer un état de cellule et l'ajouter à la liste des états
                cellStates.add(new CellState(grid4Play.getMatrix()[i][j], elementsCopy));
            }
        }

    }



    public void printState(){
        for (CellState cellState : cellStates) {
            System.out.println("Cell: " + cellState.getCell());
            System.out.println("Elements: " + cellState.getElements());
            System.out.println();
        }
    }

    private void copyElementsToGrid() {
        Grid4Play grid4Play = board4Play.getGrid4Play();
        for (CellState cellState : cellStates) {
            Cell4Play cell = cellState.getCell();
            ObservableList<Element> elements = cellState.getElements();
            // Efface les éléments actuels de la cellule
            cell.getCellsElements().clear();
            // Ajoute les éléments de la copie sauvegardée
            cell.getCellsElements().addAll(elements);
        }
    }

}
