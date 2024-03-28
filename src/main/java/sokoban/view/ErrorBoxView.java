package sokoban.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sokoban.viewmodel.ErrorBoxViewModel;

public class ErrorBoxView extends VBox {
    private Label playerError;

    private final ErrorBoxViewModel errorBoxViewModel;
    public ErrorBoxView(ErrorBoxViewModel errorBoxViewModel){
        this.errorBoxViewModel = errorBoxViewModel;
        playerError = new Label(" il doit avoir un joueur ");

        playerError.visibleProperty().bind(errorBoxViewModel.playerPresentProperty());

        getChildren().add(playerError);

    }
}
