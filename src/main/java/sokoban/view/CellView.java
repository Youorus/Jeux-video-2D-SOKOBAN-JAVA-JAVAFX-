package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sokoban.model.Element;
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
        configureBindings();
        hoverProperty().addListener(this::hoverChanged);
    }

    private void layoutControls() {
        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }

    public void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);
    }


    // protected abstract void handleMouseClicked(MouseEvent event);

    protected void setImage(ImageView image) {
        getChildren().add(image);
        image.fitWidthProperty().bind(widthProperty);
    }

    protected void imageViewUpdate(ObservableList<Element> elementObservableList) {

            if (elementObservableList.isEmpty()) {
                ImageView elementView = new ImageView(new Image(new Ground().getImage()));
                setImage(elementView);
            } else {
                for (Element element : elementObservableList) {
                    ImageView elementView = new ImageView(element.getImage());

                    setImage(elementView);
                }
            }

        }


    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.0 : 2.0);
    }
}
