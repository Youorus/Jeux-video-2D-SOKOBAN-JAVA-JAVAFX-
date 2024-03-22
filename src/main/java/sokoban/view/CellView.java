package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sokoban.model.Ground;
import sokoban.viewmodel.CellViewModel;

public class CellView extends StackPane {
    private final CellViewModel viewModel;
    private final DoubleBinding widthProperty;
    private final ImageView imageView = new ImageView(new Ground().getImage());

    CellView(CellViewModel cellViewModel, DoubleBinding cellWidthProperty) {
        this.viewModel = cellViewModel;
        this.widthProperty = cellWidthProperty;

        setAlignment(javafx.geometry.Pos.CENTER);
        layoutControls();
        configureBindings();
      //  configureClickHandler();
    }

    private void layoutControls() {
        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }

//    private void configureClickHandler() {
//        setOnMouseClicked(this::handleMouseClicked);
//    }

//    private void handleMouseClicked(MouseEvent event) {
//        if (BoiteAOutilsView.getElementObject() != null) {
//            ImageView imageView1 = new ImageView(BoiteAOutilsView.getElementObject().getImage());
//            setImage(imageView1.getImage());
//        }
//    }

    private void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);

        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Action à effectuer en cas de clic gauche
               viewModel.add();
            }
        });

        // Adapte la largeur de l'image à celle de la cellule
        imageView.fitWidthProperty().bind(widthProperty);

        // Quand la cellule change de valeur, adapter l'image affichée
        viewModel.valueProperty().addListener((obs, old, newVal) -> {
            ImageView imageView1 = new ImageView(newVal.getImage());
            setImage(imageView1);
        });

        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)
        hoverProperty().addListener(this::hoverChanged);
    }

    public void setImage(ImageView image) {
        getChildren().add(image);
        image.fitWidthProperty().bind(widthProperty);
    }

    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.0 : 1.0);
    }
}

