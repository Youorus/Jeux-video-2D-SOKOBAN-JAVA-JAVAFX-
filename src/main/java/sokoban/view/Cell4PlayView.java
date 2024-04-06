package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        configureBindings();
        updateCellImages();
        //  configureClickHandler();
    }

    public void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);

        // Adapte la largeur de l'image à celle de la cellule
        imageView.fitWidthProperty().bind(widthProperty);

        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                //System.out.println(getCellViewModel().getCellsElements().toString());
            }
        });


    }

    private void updateCellImages() {

        ObservableList<Element> elements = getCellViewModel().getCellsElements();
        // Variable pour garder le compte des boîtes dans la cellule


        // Parcourt les éléments de la cellule et met à jour l'image de la cellule
        for (Element element : elements) {
            int boxCount = 1;
            ImageView elementView = new ImageView(element.getImage());
            elementView.fitWidthProperty().bind(widthProperty());
            setImage(elementView);

            if (element instanceof Box) {
                Label boxNumberText = new Label(Integer.toString(boxCount));
                // Ajoute le style de texte pour le numéro
                boxNumberText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-fill: black;-fx-background-color: white; -fx-padding: 4px;");
                // Ajoute le numéro au-dessus de l'image de la boîte
                getChildren().add(boxNumberText);
                boxCount++; // Incrémente le numéro de boîte
            }
        }
        // Si la cellule contient au moins une boîte, ajoute un numéro au-dessus de l'image de la boîte
//        if (boxCount > 0) {

//        }
    }

}
