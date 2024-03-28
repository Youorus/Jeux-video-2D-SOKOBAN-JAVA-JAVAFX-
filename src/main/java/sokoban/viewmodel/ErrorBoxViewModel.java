package sokoban.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import sokoban.model.ErrorBox;

public class ErrorBoxViewModel {
    private final ErrorBox errorBox;
    public ErrorBoxViewModel(ErrorBox errorBox){
        this.errorBox = errorBox;
    }

    public SimpleBooleanProperty playerErrorProperty() {
        return errorBox.playerErrorProperty();
    }

    public SimpleBooleanProperty boxErrorProperty() {
        return errorBox.boxErrorProperty();
    }

    public SimpleBooleanProperty goalErrorProperty() {
        return errorBox.goalErrorProperty();
    }


}