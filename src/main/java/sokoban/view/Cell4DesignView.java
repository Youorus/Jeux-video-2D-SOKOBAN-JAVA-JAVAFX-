package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import sokoban.model.Element;
import sokoban.model.Ground;
import sokoban.viewmodel.Cell4DesignViewModel;

public class Cell4DesignView extends CellView<Cell4DesignViewModel> {
    private final ImageView imageView = new ImageView();

    Cell4DesignView(Cell4DesignViewModel cell4DesignViewModel, DoubleBinding cellWidthProperty) {
        super(cell4DesignViewModel, cellWidthProperty);
        eventClickAdd();
    }


    public void eventClickAdd(){
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                getCellViewModel().add(getCellViewModel().getToolsBoxViewModel().getElementSelect());
            }
        });

        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            imageViewAdd();
        });
    }

    private void imageViewAdd() {
        // Vérifie si l'image actuelle est nulle
        if (imageView.getImage() == null) {
            // Récupère les éléments de la cellule
            ObservableList<Element> elements = getCellViewModel().getCellsElements();

            if (elements.isEmpty()){
                ImageView elementView = new ImageView(new Image(new Ground().getImage()));
                setImage(elementView);
            }else {
                for (Element element : elements) {
                    ImageView elementView = new ImageView(element.getImage());

                    setImage(elementView);
                }
            }

            // Parcourt les éléments et les ajoute à l'image

        }
    }


}

