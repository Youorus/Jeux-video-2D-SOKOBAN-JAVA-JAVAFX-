package sokoban.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import sokoban.model.ErrorBox;

public class ErrorBoxViewModel {
    private final ErrorBox errorBox;
    public ErrorBoxViewModel(ErrorBox errorBox){
        this.errorBox = errorBox;
        //initialisation de la liste des erreur
        errorBox.errorListProperty().set(FXCollections.observableArrayList());
    }

    public SimpleBooleanProperty goalAndTargetErrorProperty() {
        return errorBox.goalAndTargetErrorProperty();
    }

    public SimpleBooleanProperty playerErrorProperty() {
        return errorBox.playerErrorProperty();
    }

    public SimpleListProperty<Label> errorListProperty() {
        return errorBox.errorListProperty();
    }

    public SimpleBooleanProperty boxErrorProperty() {
        return errorBox.boxErrorProperty();
    }
    public ObservableList<Label> getErrolist() {
        return errorBox.getErrorList();
    }

    public SimpleBooleanProperty goalErrorProperty() {
        return errorBox.goalErrorProperty();
    }


}