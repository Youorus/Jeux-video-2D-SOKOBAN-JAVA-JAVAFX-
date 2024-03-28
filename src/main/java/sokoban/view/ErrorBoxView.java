package sokoban.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sokoban.viewmodel.ErrorBoxViewModel;

import java.util.HashSet;
import java.util.Set;

public class ErrorBoxView extends VBox {
    private Label playerError;

    private Label goalError;

    private  Label boxError;
    private Label listenError;

    private Set<Label> errorList = new HashSet<>();

    private final ErrorBoxViewModel errorBoxViewModel;
    public ErrorBoxView(ErrorBoxViewModel errorBoxViewModel){
        this.errorBoxViewModel = errorBoxViewModel;
        listenError = new Label("Please correct the following error(s) : ");

        playerError = new Label("• A player is required");
        errorList.add(playerError);

        goalError = new Label("• At least one target is required");
        errorList.add(goalError);

        boxError =  new Label("• At least one box is required");
        errorList.add(boxError);



        playerError.visibleProperty().bind(errorBoxViewModel.playerErrorProperty());

        errorBoxViewModel.playerErrorProperty().addListener((olv, lod, val) ->{
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


        boxError.visibleProperty().bind(errorBoxViewModel.boxErrorProperty());

        errorBoxViewModel.boxErrorProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorList.remove(boxError);
                getChildren().remove(boxError);
            } else {
                if (!getChildren().contains(boxError)) {
                    errorList.add(boxError);
                    getChildren().add(2, boxError);
                }
            }
        });

        goalError.visibleProperty().bind(errorBoxViewModel.goalErrorProperty());

        errorBoxViewModel.goalErrorProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorList.remove(goalError);
                getChildren().remove(goalError);
            } else {
                if (!getChildren().contains(goalError)) {
                    errorList.add(goalError);
                    getChildren().add(3, goalError);
                }
            }
        });




        playerError.setStyle("-fx-text-fill: red;");
        goalError.setStyle("-fx-text-fill: red;");
        boxError.setStyle("-fx-text-fill: red;");
        listenError.setStyle("-fx-text-fill: red;");

        getChildren().add(listenError);
        getChildren().add(playerError);
        getChildren().add(goalError);
        getChildren().add(boxError);

    }
}
