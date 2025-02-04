package sokoban.view;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.model.Cell4Design;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.MenuViewModel;

public class Board4DesignView extends BoardView<Board4DesignViewModel> {


    private Label headerLabel;

    private ButtonPlay4DesignView buttonPlay4DesignView;

    private MenuView menuView;

    private ErrorBoxView errorBoxView;

    public Cell4Design[][] getMatrix(){
        return getViewModel().getMatrix();
    }
    public Board4DesignView(Stage primaryStage, Board4DesignViewModel board4DesignViewModel){
        super(primaryStage, board4DesignViewModel);
        primaryStage.setTitle("Sokoban");


        getViewModel().getBoard4Design().gridEditedProperty().addListener((ols, val, newVal) ->{
            if (newVal)
                primaryStage.setTitle("Sokoban(*)");
            if (!newVal)
                primaryStage.setTitle("Sokoban");
        });



    }

    public void createGrid () {
        this.getViewModel().getBoard4Design().getGrid4Design().gridWidthProperty().addListener((ols, val, newVal) ->{
            setCenter(null);
        });

        System.out.println(getViewModel().getBoard4Design().getGrid4Design().getGridWidth());
        System.out.println(getViewModel().getBoard4Design().getGrid4Design().getGridHeight());

        int gridWidth = getViewModel().getBoard4Design().getGrid4Design().gridWidthProperty().get();

        int gridHeight = getViewModel().getBoard4Design().getGrid4Design().getGridHeight();


        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get() - 55;
                    double availableHeight = heightProperty().get() - getHeaderBox().heightProperty().get() - getFooterBox().heightProperty().get() - 55;
                    double aspectRatio = (double) gridWidth / gridHeight;
                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth);

                },
                widthProperty(),
                heightProperty(),
                getHeaderBox().heightProperty(),
                getHeaderBox().heightProperty(),
                getFooterBox().heightProperty() );

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(gridWidth).multiply(gridHeight);
        DoubleBinding cellSizeBinding = gridWidthBinding.divide(gridWidth);

        Grid4DesignView grid4DesignView = new Grid4DesignView(getViewModel().getGridViewModel(), gridWidthBinding, gridHeightBinding);

        grid4DesignView.minHeightProperty().bind(gridHeightBinding);
        grid4DesignView.minWidthProperty().bind(gridWidthBinding);
        grid4DesignView.maxHeightProperty().bind(gridHeightBinding);
        grid4DesignView.maxWidthProperty().bind(gridWidthBinding);


        setCenter(grid4DesignView);
        createBoiteAOutils(cellSizeBinding);
    }


    private void createBoiteAOutils(DoubleBinding cellsize){
        ToolsBoxView boiteAOutilsView = new ToolsBoxView(cellsize, getViewModel().getToolsBoxViewModel(), getViewModel());
        boiteAOutilsView.setAlignment(Pos.CENTER_LEFT);
        setLeft(boiteAOutilsView);
    }

    public void createCompteur(){
        this.getViewModel().getBoard4Design().getGrid4Design().gridWidthProperty().addListener((ols, val, newVal) ->{
            headerLabel.textProperty().bind(getViewModel().filledCellsCountProperty()
                    .asString("Number of filled cells: %d of " + getViewModel().maxFilledCells()));
            headerLabel.getStyleClass().add("header");

        });

        errorBoxView = new ErrorBoxView(getViewModel().getErrorBoxViewModel());

        headerLabel = new Label();
        headerLabel.textProperty().bind(getViewModel().filledCellsCountProperty()
              .asString("Number of filled cells: %d of " + getViewModel().maxFilledCells()));
       headerLabel.getStyleClass().add("header");



       VBox headerAndErrorBox = new VBox();
       headerAndErrorBox.setAlignment(Pos.CENTER);
      headerAndErrorBox.getChildren().add(headerLabel);
      headerAndErrorBox.getChildren().add(errorBoxView);

     getHeaderBox().getChildren().addAll(headerAndErrorBox);
        getHeaderBox().setAlignment(Pos.CENTER);


        setTop(getHeaderBox());
        VBox topBox = new VBox();
        topBox.getChildren().addAll(menuView,getHeaderBox());
        setTop(topBox);

    }

    @Override
    public void createMenu() {
        this.setTop(null);

        menuView = new MenuView(new MenuViewModel(this), getViewModel());
        menuView.showConfirmationDialog1();
        setTop(menuView);
    }


    @Override
    public void createButton() {
        buttonPlay4DesignView = new ButtonPlay4DesignView(menuView,getViewModel());
        getFooterBox().setAlignment(Pos.TOP_CENTER);
        getFooterBox().setPrefHeight(70);
        getFooterBox().setPadding(new Insets(0, 0, 0, 0));
        getFooterBox().getChildren().add(buttonPlay4DesignView);
        setBottom(getFooterBox());
    }

}

