package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ErrorMessage {
    private final BooleanProperty playerError = new SimpleBooleanProperty(false);
    private final BooleanProperty targetError = new SimpleBooleanProperty(false);
    private final BooleanProperty boxError = new SimpleBooleanProperty(false);
    private final BooleanProperty filledCellsError = new SimpleBooleanProperty(false);

    public boolean isFilledCellsError() {
        return filledCellsError.get();
    }

    public BooleanProperty filledCellsErrorProperty() {
        return filledCellsError;
    }

    public void setFilledCellsError(boolean filledCellsError) {
        this.filledCellsError.set(filledCellsError);
    }



    public BooleanProperty playerErrorProperty() {
        return playerError;
    }

    public BooleanProperty targetErrorProperty() {
        return targetError;
    }

    public BooleanProperty boxErrorProperty() {
        return boxError;
    }

    public boolean isPlayerError() {
        return playerError.get();
    }

    public void setPlayerError(boolean playerError) {
        this.playerError.set(playerError);
    }

    public boolean isTargetError() {
        return targetError.get();
    }

    public void setTargetError(boolean targetError) {
        this.targetError.set(targetError);
    }

    public boolean isBoxError() {
        return boxError.get();
    }

    public void setBoxError(boolean boxError) {
        this.boxError.set(boxError);
    }
}
