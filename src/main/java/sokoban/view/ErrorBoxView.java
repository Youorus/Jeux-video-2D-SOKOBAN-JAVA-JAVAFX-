package sokoban.view;

import javafx.collections.ListChangeListener;
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

    private Label goalAndTargetError;

    private final ErrorBoxViewModel errorBoxViewModel;
    public ErrorBoxView(ErrorBoxViewModel errorBoxViewModel){
        this.errorBoxViewModel = errorBoxViewModel;
        listenError = new Label("Please correct the following error(s) : ");

        playerError = new Label("• A player is required");
        errorBoxViewModel.getErrolist().add(playerError);

        goalError = new Label("• At least one target is required");
        errorBoxViewModel.getErrolist().add(goalError);

        boxError =  new Label("• At least one box is required");
        errorBoxViewModel.getErrolist().add(boxError);

        goalAndTargetError = new Label("• Number of boxes and Targets must be equal");
        errorBoxViewModel.getErrolist().add(goalAndTargetError);

        bindingPlayerError();

        bindingBoxError();
        bindingGoalError();
        bindingGoalTargetError();


        //controlle de la liste des erreurs
        errorBoxViewModel.errorListProperty().addListener((ListChangeListener<Label>) change -> {
            if (errorBoxViewModel.getErrolist().isEmpty()) {
                if (getChildren().contains(listenError)) {
                    getChildren().remove(listenError);
                }
            } else {
                if (!getChildren().contains(listenError)) {
                    getChildren().add(0, listenError);
                }
            }
        });




        playerError.setStyle("-fx-text-fill: red;");
        goalError.setStyle("-fx-text-fill: red;");
        boxError.setStyle("-fx-text-fill: red;");
        listenError.setStyle("-fx-text-fill: red;");
        goalAndTargetError.setStyle("-fx-text-fill: red;");

        getChildren().add(0, listenError);
        getChildren().add(1, playerError);
        getChildren().add(2, goalError);
        getChildren().add(3, boxError);
        getChildren().add(4, goalAndTargetError);

    }

    private void bindingPlayerError(){
        playerError.visibleProperty().bind(errorBoxViewModel.playerErrorProperty());

        errorBoxViewModel.playerErrorProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorBoxViewModel.getErrolist().remove(playerError);
                getChildren().remove(playerError);
            } else {
                if (!getChildren().contains(playerError)) {
                    errorBoxViewModel.getErrolist().add(playerError);
                    getChildren().add(1,playerError);
                }
            }
        });
    }

    private void bindingBoxError(){
        boxError.visibleProperty().bind(errorBoxViewModel.boxErrorProperty());

        errorBoxViewModel.boxErrorProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorBoxViewModel.getErrolist().remove(boxError);
                getChildren().remove(boxError);
            } else {
                if (!getChildren().contains(boxError)) {
                    errorBoxViewModel.getErrolist().add(boxError);
                    getChildren().add( boxError);
                }
            }
        });
    }

    private void bindingGoalError() {
        goalError.visibleProperty().bind(errorBoxViewModel.goalErrorProperty());

        errorBoxViewModel.goalErrorProperty().addListener((olv, lod, val) -> {
            if (!val) {
                errorBoxViewModel.getErrolist().remove(goalError);
                getChildren().remove(goalError);
            } else {
                if (!getChildren().contains(goalError)) {
                    errorBoxViewModel.getErrolist().add(goalError);
                    getChildren().add(goalError);
                }
            }
        });
    }

    private void bindingGoalTargetError() {
        goalAndTargetError.visibleProperty().bind(errorBoxViewModel.goalAndTargetErrorProperty());
        errorBoxViewModel.goalAndTargetErrorProperty().addListener((olv, lod, val) ->{
            if (!val) {
                errorBoxViewModel.getErrolist().remove(goalAndTargetError);
                getChildren().remove(goalAndTargetError);
            } else {
                if (!getChildren().contains(goalAndTargetError)) {
                    errorBoxViewModel.getErrolist().add(goalAndTargetError);
                    getChildren().add(goalAndTargetError);
                }
            }
        });
    }

}
