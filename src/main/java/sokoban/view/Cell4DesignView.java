package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import sokoban.model.Element;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4DesignViewModel;

public class Cell4DesignView extends CellView<Cell4DesignViewModel> {
    private final ImageView imageView = new ImageView();

    Cell4DesignView(Cell4DesignViewModel cell4DesignViewModel, DoubleBinding cellWidthProperty) {
        super(cell4DesignViewModel, cellWidthProperty);
        configureBindings();
      //  configureClickHandler();
    }

    public void configureBindings() {
        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(widthProperty);


        //ajout de l'element
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                getCellViewModel().add(getCellViewModel().getToolsBoxViewModel().getElementSelect());
            }
        });

               getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
                   updateImageView();
        });


        // Adapte la largeur de l'image à celle de la cellule
        imageView.fitWidthProperty().bind(widthProperty);


    }

    private void updateImageView() {
        // Efface l'image actuelle
        imageView.setImage(null);

        // Récupère les éléments de la cellule
        ObservableList<Element> elements = getCellViewModel().getCellsElements();

        // Parcourt les éléments et les ajoute à l'image
        for (Element element : elements) {
            ImageView elementView = new ImageView(element.getImage());
            elementView.fitWidthProperty().bind(widthProperty());
//            elementView.fitHeightProperty().bind(heightProperty());

            setImage(elementView);
        }
    }

}

