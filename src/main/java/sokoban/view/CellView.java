package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sokoban.model.Ground;
import sokoban.viewmodel.CellViewModel;

public abstract class CellView<T extends CellViewModel> extends StackPane {
    protected final ImageView imageView = new ImageView(new Ground().getImage());
    protected final DoubleBinding widthProperty;
    protected final T cellViewModel;

    public CellView(T cellViewModel, DoubleBinding cellWidthProperty) {
        this.cellViewModel = cellViewModel;
        this.widthProperty = cellWidthProperty;

        setAlignment(javafx.geometry.Pos.CENTER);
        layoutControls();
       // configureBindings();
    }

    private void layoutControls() {
        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }

//    protected void configureBindings() {
//        // Liaisons pour la taille de la cellule
//        minWidthProperty().bind(widthProperty);
//        minHeightProperty().bind(widthProperty);
//
//        // Gestion du clic de souris
//        handleMouseClick();
//
//        // Liaison pour ajuster la largeur de l'image à celle de la cellule
//        imageView.fitWidthProperty().bind(widthProperty);
//
//        // Liaison pour changer l'image lorsque la valeur de la cellule change
//        cellViewModel.valueProperty().addListener((obs, oldVal, newVal) -> {
//            ImageView newImageView = new ImageView(newVal.getImage());
//            setImage(newImageView);
//        });
//
//        // Gestion du survol de la cellule
//        hoverProperty().addListener(this::hoverChanged);
//    }

   // protected abstract void handleMouseClicked(MouseEvent event);

    protected void setImage(ImageView image) {
        getChildren().add(image);
        image.fitWidthProperty().bind(widthProperty);
    }

    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.0 : 1.0);
    }
}
