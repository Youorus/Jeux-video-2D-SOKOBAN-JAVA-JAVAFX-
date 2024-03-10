package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Cell {
    private final ObjectProperty<CellValue> value = new SimpleObjectProperty<>(CellValue.ground);

    public CellValue getValue() {
        return value.get();
    }

    public void setValue(CellValue value) {
        this.value.set(value);
    }

    public boolean isEmpty() {
        return value.get() == CellValue.ground;
    }

    public ReadOnlyObjectProperty<CellValue> valueProperty() {
        return value;
    }
}
