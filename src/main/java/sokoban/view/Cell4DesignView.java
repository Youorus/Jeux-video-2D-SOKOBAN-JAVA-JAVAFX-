package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4DesignViewModel;

public class Cell4DesignView extends CellView<Cell4DesignViewModel> {
    private final ImageView imageView = new ImageView(new Ground().getImage());

    Cell4DesignView(Cell4DesignViewModel cell4DesignViewModel, DoubleBinding cellWidthProperty) {
        super(cell4DesignViewModel, cellWidthProperty);
        configureBindings();
      //  configureClickHandler();
    }

    public void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);


        //ajout de l'element
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                getCellViewModel().add(getCellViewModel().getToolsBoxViewModel().getElementSelect());
            }
        });

        // Adapte la largeur de l'image à celle de la cellule
        imageView.fitWidthProperty().bind(widthProperty);

        // Quand la cellule change de valeur, adapter l'image affichée
        getCellViewModel().valueProperty().addListener((obs, old, newVal) -> {
            ImageView imageView1 = new ImageView(newVal.getImage());
            setImage(imageView1);
        });

        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)
    }

}

