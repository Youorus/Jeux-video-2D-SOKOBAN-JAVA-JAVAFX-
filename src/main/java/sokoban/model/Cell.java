package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Cell {
    private final ObjectProperty<Element> value = new SimpleObjectProperty<>(new Ground());

    public Element getValue() {
        return value.get();
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
