package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sokoban.model.Ground;
import sokoban.viewmodel.CellViewModel;

public class CellView<T extends CellViewModel> extends StackPane {
    protected final ImageView imageView = new ImageView(new Ground().getImage());
    protected final DoubleBinding widthProperty;

    public T getCellViewModel() {
        return cellViewModel;
    }

    protected final T cellViewModel;

    public CellView(T cellViewModel, DoubleBinding cellWidthProperty) {
        this.cellViewModel = cellViewModel;
        this.widthProperty = cellWidthProperty;

        setAlignment(javafx.geometry.Pos.CENTER);
        layoutControls();
        hoverProperty().addListener(this::hoverChanged);
    }

    private void layoutControls() {
        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }

    public void configureBindings() {

    }


    // protected abstract void handleMouseClicked(MouseEvent event);

    protected void setImage(ImageView image) {
        getChildren().add(image);
        image.fitWidthProperty().bind(widthProperty);
    }

    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.0 : 1.0);
    }
}
