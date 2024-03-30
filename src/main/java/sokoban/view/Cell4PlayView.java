package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4DesignViewModel;
import sokoban.viewmodel.Cell4PlayViewModel;

public class Cell4PlayView extends StackPane {

    private final Cell4PlayViewModel cell4PlayViewModel;
    private final DoubleBinding widthProperty;

    private final ImageView imageView = new ImageView(new Ground().getImage());
    Cell4PlayView(Cell4PlayViewModel cell4PlayViewModel, DoubleBinding cellWidthProperty) {
        this.cell4PlayViewModel = cell4PlayViewModel;
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

    private void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);


        // Gère le survol de la cellule avec la souris (ajustez selon le besoin)
        hoverProperty().addListener(this::hoverChanged);
    }

    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.0 : 1.0);
    }
}
