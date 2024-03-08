package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class BoiteAOutilsView extends VBox {

    // save the clicked image
    private ImageView selectedImageView;

    public BoiteAOutilsView(DoubleBinding cellsize) {
        ImageView ground = createdImageView("ground.png");
        ImageView wall = createdImageView("wall.png");
        ImageView player = createdImageView("player.png");
        ImageView box = createdImageView("box.png");
        ImageView goal = createdImageView("goal.png");

        setSpacing(10);
        getChildren().addAll(ground, wall, player, box, goal);

        // Handle drag-and-drop events for each image
        setDragAndDropHandlers(ground);
        setDragAndDropHandlers(wall);
        setDragAndDropHandlers(player);
        setDragAndDropHandlers(box);
        setDragAndDropHandlers(goal);

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

    // Create an ImageView from the resource folder
    private ImageView createdImageView(String imageName) {
        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(50);
        imageView.setFitWidth(40);

        return imageView;
    }

    private void setDragAndDropHandlers(ImageView imageView) {
        // Add shadow on hover
        imageView.setOnMouseEntered(event -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        });

        // Remove shadow when the mouse leaves the image
        imageView.setOnMouseExited(event -> {
            imageView.setStyle(null);
        });

        // Start drag-and-drop when the mouse is pressed
        imageView.setOnDragDetected(event -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();

            // Convert the current ImageView to an Image
            Image image = imageView.getImage();

            // Add the image to the ClipboardContent
            content.putImage(image);

            db.setContent(content);

            selectedImageView = imageView;

            event.consume();
        });

        // Accept the drop and update the target cell's image
        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasImage()) {
                // Update the target cell's image
                CellView targetCell = getTargetCell(event.getX(), event.getY());
                if (targetCell != null) {
                    targetCell.setImage(selectedImageView.getImage());
                    success = true;
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    private CellView getTargetCell(double x, double y) {
        // Find the target cell based on the mouse coordinates
        for (javafx.scene.Node node : getChildren()) {
            if (node instanceof CellView) {
                CellView cellView = (CellView) node;
                if (cellView.getBoundsInParent().contains(x, y)) {
                    return cellView;
                }
            }
        }
        return null;
    }

    public ImageView getSelectedImageView() {
        return selectedImageView;
    }
}
