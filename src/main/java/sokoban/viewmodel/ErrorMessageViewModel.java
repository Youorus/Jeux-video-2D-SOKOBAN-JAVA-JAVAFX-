package sokoban.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sokoban.model.ErrorMessage;
import sokoban.model.Grid;

public class ErrorMessageViewModel {
    private final BooleanProperty playerError = new SimpleBooleanProperty(false);
    private final BooleanProperty targetError = new SimpleBooleanProperty(false);
    private final BooleanProperty boxError = new SimpleBooleanProperty(false);
    private final BooleanProperty filledCellsError = new SimpleBooleanProperty(false);
    private final Grid grid;

    public ErrorMessageViewModel(ErrorMessage errorMessage, Grid grid) {
        this.grid = grid;
        errorMessage.playerErrorProperty().addListener((observable, oldValue, newValue) -> {
            playerError.set(newValue);
            // Mettre à jour l'erreur du joueur en fonction de la présence du joueur dans la grille
            updatePlayerError(newValue && grid.hasPlayer());
        });

        errorMessage.targetErrorProperty().addListener((observable, oldValue, newValue) ->
                targetError.set(newValue));
        errorMessage.boxErrorProperty().addListener((observable, oldValue, newValue) ->
                boxError.set(newValue));
        errorMessage.filledCellsErrorProperty().addListener((observable, oldValue, newValue) ->
                filledCellsError.set(newValue));
    }

    public BooleanProperty playerErrorProperty() {
        return playerError;
    }

    public void updatePlayerError(boolean playerPresent) {
        boolean hasPlayer = grid.hasPlayer();
        playerError.set(hasPlayer);
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
