package sokoban.view;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.BoardViewModel;
import sokoban.viewmodel.BoiteAOutilsViewModel;
import sokoban.viewmodel.CellViewModel;

import java.util.Objects;

public class BoardView extends BorderPane {

    private BoiteAOutilsViewModel boiteAOutilsViewModel;
    private final BoardViewModel boardViewModel;

    private static final int GRID_WIDTH = BoardViewModel.gridWidth();

    private static final int GRID_HEIGHT = BoardViewModel.gridHeight();
    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;
    private MenuBar menuBar;

    private VBox errorBox;

    //pour le compteur
    private final Label headerLabel = new Label("");
    private final HBox headerBox = new HBox();

    // pour la boite a outils
    private final VBox leftBox = new VBox();
    public BoardView(Stage primaryStage, BoardViewModel boardViewModel){
        this.boardViewModel = boardViewModel;
        start(primaryStage);

    }

    private void start(Stage stage){
        configMainComponements(stage);

        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        String cssFile = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    private void configMainComponements(Stage stage){
        stage.setTitle("Sokoban");
        createMenue();
        createGrid();
        createCompteur();

    }

    private void createGrid () {

        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get();
                    double availableHeight = heightProperty().get() - headerBox.heightProperty().get();
                    double aspectRatio = (double) GRID_WIDTH / GRID_HEIGHT;

                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth / GRID_WIDTH) * GRID_WIDTH;
                },
                widthProperty(),
                heightProperty(),
                headerBox.heightProperty());

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(GRID_WIDTH).multiply(GRID_HEIGHT);
        DoubleBinding cellSizeBinding = gridWidthBinding.divide(GRID_WIDTH);

        GridView gridView = new GridView(boardViewModel.getGridViewModel(), gridWidthBinding, gridHeightBinding);

        gridView.minHeightProperty().bind(gridHeightBinding);
        gridView.minWidthProperty().bind(gridWidthBinding);
        gridView.maxHeightProperty().bind(gridHeightBinding);
        gridView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(gridView);
        createBoiteAOutils(cellSizeBinding);

        // Grille carrée

    }


    private void createBoiteAOutils(DoubleBinding cellsize){
        BoiteAOutilsView boiteAOutilsView = new BoiteAOutilsView(cellsize, boiteAOutilsViewModel);
        boiteAOutilsView.setAlignment(Pos.CENTER_LEFT);
        setLeft(boiteAOutilsView);
    }
    private void createCompteur(){

        headerLabel.textProperty().bind(boardViewModel.filledCellsCountProperty()
                .asString("Number of filled cells: %d of " + boardViewModel.maxFilledCells()));
        headerLabel.getStyleClass().add("header");

        errorBox = new VBox();
        errorBox.setAlignment(Pos.CENTER);
        errorBox.setSpacing(5);
        listError();
        errorBox.setVisible(false);

        if (errorBox.isVisible()) {
            errorBox.setManaged(true);
        } else {
            errorBox.setManaged(false);
        }


        VBox headerAndErrorBox = new VBox();
        headerAndErrorBox.setAlignment(Pos.CENTER);
        headerAndErrorBox.getChildren().addAll(headerLabel, errorBox);

        headerBox.getChildren().addAll(headerAndErrorBox);
        headerBox.setAlignment(Pos.CENTER);
        setTop(headerBox);
        VBox topBox = new VBox();
        topBox.getChildren().addAll(menuBar,headerBox);
        setTop(topBox);

    }
    private void createMenue(){
        MenueView menueView = new MenueView();
        menueView.showConfirmationDialog1();
        menuBar = menueView.createMenuBar();
        setTop(menuBar);
//        Menu fileMenu = new Menu("File");
//        MenuItem newMenuItem = new MenuItem("New");
//        fileMenu.getItems().add(newMenuItem);
//
//        menuBar = new MenuBar();
//        menuBar.getMenus().add(fileMenu);
//        setTop(menuBar);
    }

    private void listError(){
        String errorMessage = "Please correct the following error(s):";
        String errorMessage1 = " * A player is required";
        String errorMessage2 = " * At least one target is required";
        String errorMessage3 = " * At least one box is required";
        Label errorLabel = new Label(errorMessage);
        Label errorLabel1 = new Label(errorMessage1);
        Label errorLabel2 = new Label(errorMessage2);
        Label errorLabel3 = new Label(errorMessage3);
        errorLabel.getStyleClass().add("errorBox");
        errorLabel1.getStyleClass().add("errorBox");
        errorLabel2.getStyleClass().add("errorBox");
        errorLabel3.getStyleClass().add("errorBox");
        errorBox.getChildren().addAll(errorLabel,errorLabel1,errorLabel2,errorLabel3);

        errorBox.setVisible(true);
    }

}

