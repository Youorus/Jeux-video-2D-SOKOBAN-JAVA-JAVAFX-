package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import sokoban.model.CellValue;
import sokoban.viewmodel.CellViewModel;

public class CellView extends StackPane {
    private final CellViewModel viewModel;
    private  GridView parentGrid;
    private final DoubleBinding widthProperty;
    private final ImageView imageView = new ImageView("ground.png");

    private static final Image groundImage = new Image("ground.png");
    private static final Image wallImage = new Image("wall.png");
    private static final Image boxImage = new Image("box.png");
    private static final Image playerImage = new Image("player.png");
    private static final Image goalImage = new Image("goal.png");

    CellView(CellViewModel cellViewModel, DoubleBinding cellWidthProperty,GridView parentGrid) {
        this.viewModel = cellViewModel;
        this.widthProperty = cellWidthProperty;
        this.parentGrid = parentGrid;

        setAlignment(javafx.geometry.Pos.CENTER);
        layoutControls();
        configureBindings();
        configureMouseHandlers();
    }

    private void layoutControls() {
        imageView.setPreserveRatio(true);
        getChildren().addAll(imageView);
    }



    private void configureMouseHandlers() {
        setOnMouseClicked(this::handleMouseClicked);
        //setOnMouseDragged(this::handleMouseDragged);
       setOnMouseReleased(this::handleMouseReleased);

       // setOnDragEntered(this::handleDragEntered);
       // setOnDragExited(this::handleDragExited);
    }

//    private void handleDragEntered(DragEvent event) {
//        System.out.println("survol?");
//        // Mettez à jour l'image lorsqu'une cellule est survolée pendant un glisser-déposer
//        if (BoiteAOutilsView.getSelectedImageName() != null) {
//            setImage(BoiteAOutilsView.getSelectedImageName());
//        }
//        event.consume();
//    }

//    private void handleDragExited(DragEvent event) {
//        // Rétablissez l'image initiale lorsque la souris quitte la cellule
//        viewModel.valueProperty().getValue().ifPresent(cellValue -> setImage(imageView, cellValue));
//        event.consume();
//    }

    private void handleMouseDragged(MouseEvent event) {
        System.out.println("Survol");
        if (BoiteAOutilsView.getSelectedImageName() != null) {
            // Obtenez les coordonnées de la souris dans la grille parente
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Parcourez les cellules de la grille parente et trouvez celle survolée
            for (Node node : parentGrid.getChildren()) {
                if (node instanceof CellView) {
                    CellView cellView = (CellView) node;
                    if (cellView.getBoundsInParent().contains(mouseX, mouseY)) {
                        // Mettez à jour l'image de la cellule survolée
                        cellView.setImage(BoiteAOutilsView.getSelectedImageName());
                        break; // Sortez de la boucle une fois que la cellule est trouvée
                    }
                }
            }
        }
    }


    private void handleMouseReleased(MouseEvent event) {
        System.out.println("fin");
    }





    private void handleMouseClicked(MouseEvent event) {
        String selectedImageName = BoiteAOutilsView.getSelectedImageName();

        // Check if the selected image is the same as the image in the cell
        if (selectedImageName != null && !selectedImageName.isEmpty()) {
            Image currentImage = imageView.getImage();
            String currentImageName = currentImage.getUrl();
            System.out.println(selectedImageName);
            System.out.println(currentImageName);

            // If the image in the cell is the same as the selected image, remove it
            if (selectedImageName.equals(currentImageName)) {
                setImage("ground.png");
            } else {
                // Otherwise, place the selected image in the cell
                setImage(selectedImageName);
            }
        }
    }




    private void handleMouseExited(MouseEvent event) {

        // Désactive la gestion des événements de survol lorsque la souris quitte la cellule
        setOnMouseEntered(null);
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
            case box:
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

    public void setImage(String imageName) {
        ImageView imageView1 = new ImageView(imageName);
        imageView.setImage(imageView1.getImage());
    }

    private void hoverChanged(javafx.beans.value.ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        imageView.setOpacity(newVal ? 0.2 : 1.0);
    }
}
