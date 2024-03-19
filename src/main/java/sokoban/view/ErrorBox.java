package sokoban.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorBox extends VBox {

    public ErrorBox() {
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }

    public void addError(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.getStyleClass().add("errorBox");
        getChildren().add(errorLabel);
    }

    public void clearErrors() {
        getChildren().clear();
    }
}
