package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import sokoban.model.Element;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4DesignViewModel;
import sokoban.viewmodel.Cell4PlayViewModel;

public class Cell4PlayView extends CellView<Cell4PlayViewModel> {
    private final ImageView imageView = new ImageView(new Ground().getImage());
    Cell4PlayView(Cell4PlayViewModel cell4PlayViewModel, DoubleBinding cellWidthProperty) {
       super(cell4PlayViewModel, cellWidthProperty);
        configureBindings();
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

//
//        getCellViewModel().valueProperty().addListener((obs, old, newVal) -> {
//            ImageView imageView1 = new ImageView(newVal.getImage());
//            setImage(imageView1);
//
//        });

        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            // Supprime toutes les images précédentes de la cellule
            getChildren().clear();

            // Parcourt les nouveaux éléments et ajoute leur image à la cellule
            for (Element element : change.getList()) {
                ImageView elementView = new ImageView(element.getImage());
                elementView.fitWidthProperty().bind(widthProperty());
                setImage(elementView);
            }
        });

        // Configure l'image de base de la cellule (Ground)
        //setImage(imageView);

        // Configure l'imageView pour qu'elle s'adapte à la largeur de la cellule
//        imageView.setPreserveRatio(true);
//        imageView.fitWidthProperty().bind(widthProperty);

        // Ajoute l'imageView à la vue de la cellule

        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)

    }




}
