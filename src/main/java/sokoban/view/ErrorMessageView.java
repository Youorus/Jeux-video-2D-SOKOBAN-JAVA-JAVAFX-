package sokoban.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorMessageView extends VBox {
    public ErrorMessageView() {
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }

    public void addError(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.setStyle("-fx-text-fill: red;");
        getChildren().add(errorLabel);
    }

//    public void updateErrors(boolean playerError) {
//        // Mettre à jour les étiquettes d'erreur en fonction des propriétés du modèle
//        if (playerError) {
//            addError("Il faut placer un joueur.");
//        } else {
//            removeError("Il faut placer un joueur.");
//        }
//    }
void removeError(String errorMessage) {
        getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().equals(errorMessage));
    }


}
