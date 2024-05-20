package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import sokoban.model.*;
import sokoban.viewmodel.Cell4PlayViewModel;

import static javafx.scene.input.KeyCode.Y;
import static javafx.scene.input.KeyCode.Z;

public class Cell4PlayView extends CellView<Cell4PlayViewModel> {
    private final ImageView imageView = new ImageView(new Ground().getImage());


    private boolean boxesNumbered = false;


    Cell4PlayView(Cell4PlayViewModel cell4PlayViewModel, DoubleBinding cellWidthProperty) {
        super(cell4PlayViewModel, cellWidthProperty);
        updateCellImages();
        setFocusTraversable(true); // focus sur l'entrée des touchees
        setOnKeyPressed(this::handleKeyPressed);

        eventClickCellPlay();


        getCellViewModel().playerWinProperty().addListener((obs, oldValue, newValue) ->{
           setOnKeyPressed(null);
        });



        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            imageViewUpdate(getCellViewModel().getCellsElements());
            updateCellImages();
        });


    }


    private void updateCellImages() {
        // Effacer tous les éléments de la cellule
        getChildren().clear();

        ObservableList<Element> elements = getCellViewModel().getCellsElements();

        // Afficher l'image du sol
        ImageView groundView = new ImageView(new Ground().getImage());
        setImage(groundView);

        // Parcourt les éléments de la cellule dans l'ordre inverse
        for (int i = elements.size() - 1; i >= 0; i--) {

            Element element = elements.get(i);

            ImageView elementView = new ImageView(element.getImage());
            setImage(elementView);

            if (element instanceof Mushroom){
                    elementView.visibleProperty().bind(getCellViewModel().getBoard4Play().getGrid4Play().showMushroomProperty()); //masquer le mushroom au lancement de l'app
            }


            if ( element instanceof Box) { // Vérifie si les boîtes n'ont pas encore été numérotées
                Box.increment();
                int x = Box.getBoxNumber();

                // Crée le texte pour numéroter la boîte
                Label boxNumberText = new Label(Integer.toString(x));
                // Ajoute le style de texte pour le numéro
                boxNumberText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-fill: black; -fx-background-color: white; -fx-padding: 4px;");
                // Positionne le numéro au-dessus de l'image de la boîte
                StackPane.setAlignment(boxNumberText, Pos.CENTER);
                // Ajoute le numéro au-dessus de l'image de la boîte
                getChildren().add(boxNumberText);
            }
        }
    }


    public void eventClickCellPlay(){
        this.setOnMouseClicked(event -> {
            if (getCellViewModel().ClickOnMushroom()){
                getCellViewModel().getBoard4Play().getGrid4Play().removeMushroom();
                getCellViewModel().getBoard4Play().getGrid4Play().getCell4Play().addMushroomToRandomEmptyCell();
            }
        });

    }

    private void handleKeyPressed(KeyEvent event) {

        // Vérifie si la combinaison Ctrl + Z est pressée
        if (event.isControlDown() && event.getCode() == Z) {
            // Ctrl+Z pour annuler
            getCellViewModel().getBoard4Play().undoMove();
        } else if (event.isControlDown() && event.getCode() == Y) {
            // Ctrl+Y pour refaire
            getCellViewModel().getBoard4Play().redoMove();
        }else{
            switch (event.getCode()) {
                case Z:
                    //deplacement verrs le haut
                    getCellViewModel().getBoard4Play().executeMove(Direction.UP);
                    //getCellViewModel().movePlayerUp();
                    break;
                case Q:
                    //deplacement du joueur sur la gauche
                    getCellViewModel().getBoard4Play().executeMove(Direction.LEFT);
                    break;
                case S:
                    //deplacement du joueur sur la droite
                    getCellViewModel().getBoard4Play().executeMove(Direction.DOWN);
                    break;
                case D:
                    //deplacement du joueur vers le bas
                    getCellViewModel().getBoard4Play().executeMove(Direction.RIGHT);
                    break;
                default:
                    // Autre touche pressée
                    break;
            }
        }


    }





}

