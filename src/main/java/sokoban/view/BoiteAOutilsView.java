package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sokoban.model.*;
import sokoban.viewmodel.BoiteAOutilsViewModel;

import java.util.ArrayList;
import java.util.List;

public class BoiteAOutilsView extends VBox {

    private BoiteAOutilsViewModel viewModel;
    private final List<Element> elements;

    public BoiteAOutilsView(DoubleBinding cellSize, BoiteAOutilsViewModel viewModel) {
        this.viewModel = viewModel;
        this.elements = createElements();

        setSpacing(10);

        for (Element element : elements) {
            ImageView imageView = createImageView(element.getImage());
            getChildren().add(imageView);
            setClickHandlers(imageView, element);
        }

        cellSize.addListener((obs, oldVal, newSize) -> adjustImageViewSizes(newSize.doubleValue()));
        adjustImageViewSizes(cellSize.get());
    }

    private List<Element> createElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(new Ground());
        elements.add(new Wall());
        elements.add(new Player());
        elements.add(new Box());
        elements.add(new Goal());
        // Ajoutez d'autres éléments selon vos besoins
        return elements;
    }

    private void adjustImageViewSizes(double size) {
        getChildren().forEach(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
            }
        });
    }

    private ImageView createImageView(String imageName) {
        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(50);
        imageView.setFitWidth(40);
        return imageView;
    }

    private void setClickHandlers(ImageView imageView, Element element) {
        imageView.setOnMouseClicked(event -> {
            viewModel.setSelectedElement(element);
        });
    }
}
