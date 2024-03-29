package sokoban.model;

import javafx.beans.property.SimpleObjectProperty;

public class ToolsBox {
    public Element getElementSelect() {
        return elementSelect.get();
    }

    public SimpleObjectProperty<Element> elementSelectProperty() {
        return elementSelect;
    }

    public void setElementSelect(Element elementSelect) {
        this.elementSelect.set(elementSelect);
    }

    private final SimpleObjectProperty<Element> elementSelect = new SimpleObjectProperty<>();
}
