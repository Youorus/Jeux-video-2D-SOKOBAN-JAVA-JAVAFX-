package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BoiteAOutilsView extends VBox {

    // sauver l'image clicker
    private ImageView selectedImageView;

    public BoiteAOutilsView(DoubleBinding cellsize) {
        ImageView ground = createdImageView("ground.png");
        ImageView wall = createdImageView("wall.png");
        ImageView player = createdImageView("player.png");
        ImageView box = createdImageView("box.png");
        ImageView goal = createdImageView("goal.png");



        setSpacing(10);
        getChildren().addAll(ground, wall, player, box, goal);

        // Gérer les événements de survol et de clic pour chaque image
        setEventHandlers(ground, "ground.png");
        setEventHandlers(wall, "wall.png");
        setEventHandlers(player, "player.png");
        setEventHandlers(box, "box.png");
        setEventHandlers(goal, "goal.png");

        cellsize.addListener((obs, oldVal, newSize) -> {
            adjustImageViewSizes(newSize.doubleValue());
        });

        adjustImageViewSizes(cellsize.get());

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

    // Création d'une image à partir de de mon dossier ressource
    private ImageView createdImageView(String imageName) {
        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(50);
        imageView.setFitWidth(40);

        return imageView;
    }

    private void setEventHandlers(ImageView imageView, String imageName) {
        // rajout de l'ombre au survol
        imageView.setOnMouseEntered(event -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        });
        // Enlever l'ombre lorsque la souris quitte l'image
        imageView.setOnMouseExited(event -> {
            imageView.setStyle(null);
        });
        // mémoriser l'image clicked et mettre à jour le label
        imageView.setOnMouseClicked(event -> {
            selectedImageView = imageView;
            System.out.println(imageName);
        });
    }

    public ImageView getSelectedImageView() {
        return selectedImageView;
    }
}
