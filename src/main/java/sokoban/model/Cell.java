package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final ObjectProperty<Element> value = new SimpleObjectProperty<>(new Ground());

    public Set<Element> getCellsElements() {
        return cellsElements;
    }

    private Set<Element> cellsElements = new HashSet<>();

    public Element getValue() {
        return value.get();
    }

    public void add(Element element){
        cellsElements.add(element);
    }


    public void setValue(Element value) {
        this.value.set(value);
    }

    public boolean isEmpty() {
        return value.get() instanceof Ground;
    }

    public ReadOnlyObjectProperty<Element> valueProperty() {
        return value;
    }
}
