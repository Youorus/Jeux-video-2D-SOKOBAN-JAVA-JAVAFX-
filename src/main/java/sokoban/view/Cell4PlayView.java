package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
        setOnMouseClicked(event -> handleClickCellPlay());



        getCellViewModel().playerWinProperty().addListener((obs, oldValue, newValue) ->{
           setOnKeyPressed(null);
        });

        getCellViewModel().getBoard4Play().getGrid4Play().showMushroomProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                setOnKeyPressed(null);
                setOnMouseClicked(null);
            } else {
                // Rétablissez les gestionnaires d'événements lorsque la valeur de la propriété showMushroomProperty est fausse
                setOnKeyPressed(this::handleKeyPressed);
                setOnMouseClicked(event -> handleClickCellPlay());
            }
        });


        getCellViewModel().getCellsElements().addListener((ListChangeListener<Element>) change -> {
            updateCellImages();
            getCellViewModel().getBoard4Play().getGrid4Play().updateBoxOnGoalCount();
        });


    }


    private void updateCellImages() {
        // Effacer tous les éléments de la cellule
        getChildren().clear();

        ObservableList<Element> elements = getCellViewModel().getCellsElements();

        // Afficher l'image du sol
        ImageView groundView = new ImageView(new Ground().getImage());
        setImage(groundView);

        // Parcours les éléments de la cellule
        for (Element element : elements) {
            ImageView elementView = new ImageView(element.getImage());
            setImage(elementView);

            if (element instanceof Mushroom) {
                elementView.visibleProperty().bind(getCellViewModel().getBoard4Play().getGrid4Play().showMushroomProperty());
            }

            if (element instanceof Box) {
                Box box = (Box) element;
                // Créer un label avec le numéro unique de la boîte
                Label boxNumberText = new Label(Integer.toString(box.getBoxNumber()));
                // Ajouter le style au numéro de la boîte
                boxNumberText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-fill: black; -fx-background-color: white; -fx-padding: 4px;");
                // Positionner le numéro au-dessus de l'image de la boîte
                StackPane.setAlignment(boxNumberText, Pos.CENTER);
                // Ajouter le numéro au-dessus de l'image de la boîte
                getChildren().add(boxNumberText);
            }
        }
    }



    public void handleClickCellPlay() {
        if (getCellViewModel().ClickOnMushroom()) {
            getCellViewModel().getBoard4Play().undoIfCellsChange();
            getCellViewModel().getBoard4Play().getGrid4Play().setMoveCount(getCellViewModel().getBoard4Play().getGrid4Play().getMoveCount() + 20);
            getCellViewModel().getBoard4Play().getGrid4Play().moveBoxesToRandomEmptyCells();
            getCellViewModel().getBoard4Play().getGrid4Play().removeMushroom();
            getCellViewModel().getBoard4Play().getGrid4Play().getCell4Play().addMushroomToRandomEmptyCell();

        }
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

