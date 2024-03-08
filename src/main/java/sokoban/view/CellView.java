package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import sokoban.model.CellValue;
import sokoban.viewmodel.CellViewModel;
import sokoban.viewmodel.GridViewModel;

import java.util.Objects;

public class CellView extends StackPane {
    private final CellViewModel viewModel;
    private final DoubleBinding widthProperty;
    private final ImageView imageView = new ImageView("ground.png");

    private static final Image groundImage = new Image("ground.png");
    private static final Image wallImage = new Image("wall.png");
    private static final Image boxImage = new Image("box.png");
    private static final Image playerImage = new Image("player.png");
    private static final Image goalImage = new Image("goal.png");

    CellView(CellViewModel cellViewModel, DoubleBinding cellWidthProperty) {
        this.viewModel = cellViewModel;
        this.widthProperty = cellWidthProperty;

        setAlignment(Pos.CENTER);
        layoutControls();
        configureBindings();
        configureDragAndDrop();
    }

    private void layoutControls() {

        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }

    private void configureDragAndDrop() {
        setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasImage()) {
                // Update the cell's image
                setImage(db.getImage());
                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);

        // Adapte la largeur de l'image à celle de la cellule
        imageView.fitWidthProperty().bind(widthProperty);


        // Quand la cellule change de valeur, adapter l'image affichée
        viewModel.valueProperty().addListener((obs, old, newVal) -> setImage(imageView, newVal));

        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)
        hoverProperty().addListener(this::hoverChanged);
    }

    private void setImage(ImageView imageView, CellValue cellValue) {
        switch (cellValue) {
            case wall:
                imageView.setImage(wallImage);
                break;
            case boxe:
                imageView.setImage(boxImage);
                break;
            case player:
                imageView.setImage(playerImage);
                break;
            case goal:
                imageView.setImage(goalImage);
                break;
            default:
                imageView.setImage(groundImage);
        }
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    private void hoverChanged(ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.2 : 1.0);
    }
}