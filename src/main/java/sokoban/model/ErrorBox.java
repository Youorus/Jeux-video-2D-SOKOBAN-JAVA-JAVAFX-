package sokoban.model;

import javafx.beans.property.SimpleBooleanProperty;

public class ErrorBox {
    private final SimpleBooleanProperty playerError = new SimpleBooleanProperty(true);

    private final SimpleBooleanProperty boxError = new SimpleBooleanProperty(true);

    private final SimpleBooleanProperty goalError = new SimpleBooleanProperty(true);

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