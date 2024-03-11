package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import sokoban.model.*;

import java.util.ArrayList;
import java.util.List;

public class BoiteAOutilsView extends VBox {

    // Save the clicked image
    private static String selectedImageName;

    public BoiteAOutilsView(DoubleBinding cellSize) {
        List<Element> elements = createElements();

        setSpacing(10);

        for (Element element : elements) {
            ImageView imageView = createdImageView(element.getImage());
            getChildren().add(imageView);
            setClickHandlers(imageView, element);
        }

        cellSize.addListener((obs, oldVal, newSize) -> {
            adjustImageViewSizes(newSize.doubleValue());
        });

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
            if (node instanceof ImageView imageView) {
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
                if (imageView.getImage() != null && imageView.getImage().getUrl().equals(selectedImageName)) {
                    imageView.setStyle("-fx-padding: 10;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 50;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: blue;");
                } else {
                    imageView.setStyle(null);
                }
                imageView.applyCss();
            }
        });
    }

    // Create an ImageView from the resource folder
    private ImageView createdImageView(String imageName) {
        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(50);
        imageView.setFitWidth(40);

        return imageView;
    }


    private void setClickHandlers(ImageView imageView, Element element) {
        imageView.setOnMouseClicked(event -> {
            // Mettre à jour l'image sélectionnée
            selectedImageName = element.getImage();
            adjustImageViewSizes(imageView.getFitWidth());  // Mettre à jour le style immédiatement
        });
    }

    public static String getSelectedImageName() {
        Image image = new ImageView(selectedImageName).getImage();
        return image.getUrl();
    }
}
