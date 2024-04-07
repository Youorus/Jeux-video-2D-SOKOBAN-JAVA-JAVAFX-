package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    public ObservableList<Element> getCellsElements() {
        return cellsElements;
    }
    private ObservableList<Element> cellsElements = FXCollections.observableArrayList();


public boolean isEmpty(){
    return cellsElements.isEmpty();
}

}
