package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Cell4Play {
    private final ObjectProperty<Element> value = new SimpleObjectProperty<>(new Ground());

    public void setValue(Element value) {
        this.value.set(value);
    }
}
