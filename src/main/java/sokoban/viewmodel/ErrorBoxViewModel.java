package sokoban.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import sokoban.model.ErrorBox;

public class ErrorBoxViewModel {
    private final ErrorBox errorBox;
    public ErrorBoxViewModel(ErrorBox errorBox){
        this.errorBox = errorBox;
    }

    public boolean isPlayerPresent() {
        return errorBox.isPlayerPresent();
    }
    public SimpleBooleanProperty playerPresentProperty() {
        return errorBox.playerPresentProperty();
    }

    public void setPlayerPresent(boolean playerPresent) {
        errorBox.setPlayerPresent(playerPresent);
    }

}