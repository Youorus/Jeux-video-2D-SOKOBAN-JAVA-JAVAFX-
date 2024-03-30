package sokoban.view;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.model.Cell4Design;
import sokoban.viewmodel.Board4DesignViewModel;

import java.util.Objects;

public class Board4DesignView extends BorderPane {

    private final Board4DesignViewModel board4DesignViewModel;

    private static final int GRID_WIDTH = Board4DesignViewModel.gridWidth();

    private static final int GRID_HEIGHT = Board4DesignViewModel.gridHeight();
    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;

    private final PlayButtonView playButtonView;
    private MenuBar menuBar;


    //pour le compteur
    private final Label headerLabel = new Label("");
    private final HBox headerBox = new HBox();
    private ErrorBoxView errorBoxView;

    // pour la boite a outils
    private final VBox leftBox = new VBox();

    public Cell4Design[][] getMatrix(){
        return board4DesignViewModel.getMatrix();
    }
    public Board4DesignView(Stage primaryStage, Board4DesignViewModel board4DesignViewModel){
        errorBoxView = new ErrorBoxView(board4DesignViewModel.getErrorBoxViewModel());
        this.playButtonView = new PlayButtonView(board4DesignViewModel);
        this.board4DesignViewModel = board4DesignViewModel;
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
        createPlayButton();

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

        Grid4DesignView grid4DesignView = new Grid4DesignView(board4DesignViewModel.getGridViewModel(), gridWidthBinding, gridHeightBinding);

        grid4DesignView.minHeightProperty().bind(gridHeightBinding);
        grid4DesignView.minWidthProperty().bind(gridWidthBinding);
        grid4DesignView.maxHeightProperty().bind(gridHeightBinding);
        grid4DesignView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(grid4DesignView);
        createBoiteAOutils(cellSizeBinding);

        // Grille carrée

    }


    private void createBoiteAOutils(DoubleBinding cellsize){
        ToolsBoxView boiteAOutilsView = new ToolsBoxView(cellsize, board4DesignViewModel.getToolsBoxViewModel());
        boiteAOutilsView.setAlignment(Pos.CENTER_LEFT);
        setLeft(boiteAOutilsView);
    }


    private void createCompteur(){

        headerLabel.textProperty().bind(board4DesignViewModel.filledCellsCountProperty()
                .asString("Number of filled cells: %d of " + board4DesignViewModel.maxFilledCells()));
        headerLabel.getStyleClass().add("header");



        VBox headerAndErrorBox = new VBox();
        headerAndErrorBox.setAlignment(Pos.CENTER);
        headerAndErrorBox.getChildren().addAll(headerLabel, errorBoxView);

        headerBox.getChildren().addAll(headerAndErrorBox);
        headerBox.setAlignment(Pos.CENTER);
        setTop(headerBox);
        VBox topBox = new VBox();
        topBox.getChildren().addAll(menuBar,headerBox);
        setTop(topBox);

    }

    private void createPlayButton () {
        playButtonView.setAlignment(Pos.TOP_CENTER);
        playButtonView.setPadding(new Insets(16, 16, 16, 16));
        playButtonView.setPrefHeight(40);
        setBottom(playButtonView);
    }

    private void createMenue(){
        MenueView menueView = new MenueView(this);
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


}

