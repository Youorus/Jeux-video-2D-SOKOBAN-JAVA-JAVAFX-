package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ErrorBox {
    private final SimpleBooleanProperty playerPresent = new SimpleBooleanProperty(true);


    public boolean isPlayerPresent() {
        return playerPresent.get();
    }

    public SimpleBooleanProperty playerPresentProperty() {
        return playerPresent;
    }

    public void setPlayerPresent(boolean playerPresent) {
        this.playerPresent.set(playerPresent);
    }


}