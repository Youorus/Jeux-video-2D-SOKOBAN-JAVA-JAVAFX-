package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
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

        getCellViewModel().valueProperty().addListener((obs, old, newVal) -> {
            ImageView imageView1 = new ImageView(newVal.getImage());
            setImage(imageView1);

        });



        // Configure l'imageView pour qu'elle s'adapte à la largeur de la cellule
//        imageView.setPreserveRatio(true);
//        imageView.fitWidthProperty().bind(widthProperty);

        // Ajoute l'imageView à la vue de la cellule

        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)

    }

}
