package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
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
                System.out.println(getCellViewModel().getCellsElements().toString());
            }
        });


    }

    private void updateCellImages() {
        // Efface toutes les images actuelles des cellules


                // Récupère les éléments de la cellule actuelle
                ObservableList<Element> elements = getCellViewModel().getCellsElements();
                System.out.println(elements);
                // Parcourt les éléments de la cellule et met à jour l'image de la cellule
                for (Element element : elements) {
                    ImageView elementView = new ImageView(element.getImage());
                    elementView.fitWidthProperty().bind(widthProperty());
                    setImage(elementView);
                }


    }
}
