package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BoiteAOutilsView extends VBox {

    // Save the clicked image
    private static ImageView selectedImageView;


    public BoiteAOutilsView(DoubleBinding cellSize) {
        ImageView ground = createdImageView("ground.png");
        ImageView wall = createdImageView("wall.png");
        ImageView player = createdImageView("player.png");
        ImageView box = createdImageView("box.png");
        ImageView goal = createdImageView("goal.png");

        setSpacing(10);
        getChildren().addAll(ground, wall, player, box, goal);

        // Handle click events for each image
        setClickHandlers(ground);
        setClickHandlers(wall);
        setClickHandlers(player);
        setClickHandlers(box);
        setClickHandlers(goal);

        cellSize.addListener((obs, oldVal, newSize) -> {
            adjustImageViewSizes(newSize.doubleValue());
        });

        adjustImageViewSizes(cellSize.get());
    }

    private void adjustImageViewSizes(double size) {
        getChildren().forEach(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
                if (imageView == selectedImageView) {
                    imageView.setStyle("-fx-border-color: blue; -fx-border-width: 10;");
                } else {
                    imageView.setStyle(null);
                }

                // Gérer l'événement de clic pour mettre à jour l'image sélectionnée
                setClickHandlers(imageView);

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

    private void setClickHandlers(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            // Mettre à jour l'image sélectionnée
            selectedImageView = imageView;
            adjustImageViewSizes(imageView.getFitWidth());  // Mettre à jour le style immédiatement
        });
    }

    public static ImageView getSelectedImageView() {
        return selectedImageView;
    }
}
