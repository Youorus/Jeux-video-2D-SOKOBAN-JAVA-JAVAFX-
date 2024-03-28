package sokoban.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sokoban.viewmodel.ErrorBoxViewModel;

import java.util.HashSet;
import java.util.Set;

public class ErrorBoxView extends VBox {
    private Label playerError;

    private Label targetError;

    private  Label boxError;
    private Label listenError;

    private Set<Label> errorList = new HashSet<>();

    private final ErrorBoxViewModel errorBoxViewModel;
    public ErrorBoxView(ErrorBoxViewModel errorBoxViewModel){
        this.errorBoxViewModel = errorBoxViewModel;
        listenError = new Label("Please correct the following error(s) : ");

        playerError = new Label("• A player is required");
        errorList.add(playerError);

        targetError = new Label("• At least one target is required");
        errorList.add(targetError);

        boxError =  new Label("• At least one box is required");
        errorList.add(boxError);



        playerError.visibleProperty().bind(errorBoxViewModel.playerPresentProperty());

        errorBoxViewModel.playerPresentProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorList.remove(playerError);
                getChildren().remove(playerError);
            } else {
                if (!getChildren().contains(playerError)) {
                    errorList.add(playerError);
                    getChildren().add(1, playerError);
                }
            }
        });






        playerError.setStyle("-fx-text-fill: red;");
        targetError.setStyle("-fx-text-fill: red;");
        boxError.setStyle("-fx-text-fill: red;");
        listenError.setStyle("-fx-text-fill: red;");

        getChildren().add(listenError);
        getChildren().add(playerError);
        getChildren().add(targetError);
        getChildren().add(boxError);

    }
}
