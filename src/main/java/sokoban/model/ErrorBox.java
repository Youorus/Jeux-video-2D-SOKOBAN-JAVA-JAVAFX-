package sokoban.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class ErrorBox {

    private final SimpleBooleanProperty goalAndTargetError = new SimpleBooleanProperty(false);
    private SimpleListProperty<Label> errorList = new SimpleListProperty<>();
    private final SimpleBooleanProperty playerError = new SimpleBooleanProperty(true);

    private final SimpleBooleanProperty boxError = new SimpleBooleanProperty(true);

    private final SimpleBooleanProperty goalError = new SimpleBooleanProperty(true);

    public ObservableList<Label> getErrorList() {
        return errorList.get();
    }

    public SimpleBooleanProperty goalAndTargetErrorProperty() {
        return goalAndTargetError;
    }

    public SimpleListProperty<Label> errorListProperty() {
        return errorList;
    }

    public SimpleBooleanProperty boxErrorProperty() {
        return boxError;
    }

    public SimpleBooleanProperty goalErrorProperty() {
        return goalError;
    }

    public SimpleBooleanProperty playerErrorProperty() {
        return playerError;
    }


}