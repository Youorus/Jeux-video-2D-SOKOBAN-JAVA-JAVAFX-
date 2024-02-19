package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Case {
    // mettre unr structure qui permette que plusieur chose sur une case(collection, liste, map) qui soit observable afin que quand c'est modifié
    // et que la vue change
    private final ObjectProperty<CaseValue> value = new SimpleObjectProperty<>(CaseValue.TERRAIN);
    CaseValue getValue() {
        return value.getValue();
    }
    boolean isEmpty() {
        return value.get() == CaseValue.TERRAIN;
    }
}
