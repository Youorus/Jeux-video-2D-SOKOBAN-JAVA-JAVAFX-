package sokoban.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import sokoban.model.Element;

public class ToolsBoxViewModel {
    private static final ObjectProperty<Element> selectedElement = new SimpleObjectProperty<>();

    public ObjectProperty<Element> selectedElementProperty() {
        return selectedElement;
    }

    public Element getSelectedElement() {
        return selectedElement.get();
    }

    public void setSelectedElement(Element element) {
        selectedElement.set(element);
    }
}