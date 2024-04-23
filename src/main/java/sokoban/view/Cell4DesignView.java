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

    Cell4DesignView(Cell4DesignViewModel cell4DesignViewModel, DoubleBinding cellWidthProperty) {
        super(cell4DesignViewModel, cellWidthProperty);
        eventClickAdd();

        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            getCellViewModel().gridEditedProperty().set(true);
        });

        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            imageViewUpdate(getCellViewModel().getCellsElements());
        });
    }


    public void eventClickAdd(){
        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                getCellViewModel().add(getCellViewModel().getToolsBoxViewModel().getElementSelect());


            }
        });

    }


}


