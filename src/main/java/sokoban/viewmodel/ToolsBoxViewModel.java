package sokoban.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import sokoban.model.Element;
import sokoban.model.ToolsBox;

public class ToolsBoxViewModel {

    private final ToolsBox toolsBox;

    public ToolsBoxViewModel(ToolsBox toolsBox){
        this.toolsBox = toolsBox;
    }

    public Element getElementSelect() {
        return toolsBox.getElementSelect();
    }

    public SimpleObjectProperty<Element> elementSelectProperty() {
        return toolsBox.elementSelectProperty();
    }

    public void setElementSelect(Element elementSelect) {
        toolsBox.setElementSelect(elementSelect);
    }

}