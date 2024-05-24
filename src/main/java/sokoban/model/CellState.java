package sokoban.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CellState {

    private final Cell4Play cell;
    private final ObservableList<Element> elements;

    public CellState(Cell4Play cell, ObservableList<Element> elements) {
        this.cell = cell;
        this.elements = FXCollections.observableArrayList(elements);
    }

    public Cell4Play getCell() {
        return cell;
    }

    public ObservableList<Element> getElements() {
        return elements;
    }
    public void restore() {
        cell.getCellsElements().setAll(elements);
    }
}

