package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Case {
    private final ObjectProperty<CaseValue> value = new SimpleObjectProperty<>(CaseValue.TERRAIN);
    CaseValue getValue() {
        return value.getValue();
    }
    boolean isEmpty() {
        return value.get() == CaseValue.TERRAIN;
    }
}
