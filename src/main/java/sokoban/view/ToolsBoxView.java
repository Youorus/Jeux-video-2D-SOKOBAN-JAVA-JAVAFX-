package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sokoban.model.*;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.ToolsBoxViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToolsBoxView extends VBox {

    public Element getElementSelect() {
        return elementSelect.get();
    }

    public SimpleObjectProperty<Element> elementSelectProperty() {
        return elementSelect;
    }

    public void setElementSelect(Element elementSelect) {
        this.elementSelect.set(elementSelect);
    }

    private final SimpleObjectProperty<Element> elementSelect = new SimpleObjectProperty<>();
    private final List<Element> elements;
    private final ToolsBoxViewModel toolsBoxViewModel;

    private final Board4DesignViewModel board4DesignViewModel;
    public ToolsBoxView(DoubleBinding cellSize, ToolsBoxViewModel toolsBoxViewModel, Board4DesignViewModel board4DesignViewModel) {
        this.toolsBoxViewModel = toolsBoxViewModel;
        this.board4DesignViewModel = board4DesignViewModel;
        this.elements = createElements();

        setSpacing(10);

        for (Element element : elements) {
            VBox vBoxElement = new VBox();
            //vBoxElement.setBorder(Border.stroke(Color.BLUE));
            vBoxElement.setPadding(new Insets(1));
            ImageView imageView = createImageView(element.getImage());
            vBoxElement.getChildren().add(imageView);
            getChildren().add(vBoxElement);
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
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
            }
        });
    }

    private ImageView createImageView(String imageName) {
        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(37);
        imageView.setFitWidth(37);
        return imageView;
    }

    private void setClickHandlers(ImageView imageView, Element element) {
        imageView.setOnMouseClicked(event -> {
           setElementSelect(element);
           toolsBoxViewModel.elementSelectProperty().bind(elementSelect);

            for (Node node : getChildren()) {
                if (node instanceof VBox) {
                    ((VBox) node).setBorder(null); // Supprimer la bordure
                }
            }

            // Appliquer la bordure à l'élément sélectionné
            ((VBox) imageView.getParent()).setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));// Ajouter une bordure bleue
        });

    }

}
