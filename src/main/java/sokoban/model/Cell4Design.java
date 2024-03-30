package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashSet;
import java.util.Set;

public class Cell4Design extends Cell {
    public Set<Element> getCellsElements() {
        return cellsElements;
    }
    private Set<Element> cellsElements = new HashSet<>();

    public void add(Element element){
        cellsElements.clear();
        cellsElements.add(element);
    }

}
