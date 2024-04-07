package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sokoban.model.Board4Play;
import sokoban.model.Box;
import sokoban.model.Element;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4PlayViewModel;

import static sokoban.model.Grid4Design.getGridHeight;
import static sokoban.model.Grid4Design.getGridWidth;

public class Cell4PlayView extends CellView<Cell4PlayViewModel> {
    private final ImageView imageView = new ImageView(new Ground().getImage());

    Cell4PlayView(Cell4PlayViewModel cell4PlayViewModel, DoubleBinding cellWidthProperty) {
        super(cell4PlayViewModel, cellWidthProperty);
        updateCellImages();
        setFocusTraversable(true); // focus sur l'entrée des touchees
        setOnKeyPressed(this::handleKeyPressed);
    }


    private void updateCellImages() {
        int boxCount = 0;
        ObservableList<Element> elements = getCellViewModel().getCellsElements();
        // Initialise le numéro de la boîte à 1
        // Parcourt les éléments de la cellule et met à jour l'image de la cellule
        for (Element element : elements) {
            ImageView elementView = new ImageView(element.getImage());
            elementView.fitWidthProperty().bind(widthProperty());
            setImage(elementView);

            if (element.equals(new Box())) {
                // Pas besoin d'incrémenter boxCount ici
                Label boxNumberText = new Label(Integer.toString(boxCount++));
                // Ajoute le style de texte pour le numéro
                boxNumberText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-fill: black;-fx-background-color: white; -fx-padding: 4px;");
                // Positionne le numéro au-dessus de l'image de la boîte
                StackPane.setAlignment(boxNumberText, Pos.CENTER);
                // Ajoute le numéro au-dessus de l'image de la boîte
                getChildren().add(boxNumberText);
            }
        }
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.W) {
            // Déclencher votre événement ici
            System.out.println("Touche W pressée !");
            // Vous pouvez invoquer une méthode ou émettre un événement personnalisé ici
        }
    }
}

