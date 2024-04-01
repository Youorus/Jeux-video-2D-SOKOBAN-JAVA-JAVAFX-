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

public class Board4DesignView extends BoardView<Board4DesignViewModel> {


    private Label headerLabel;

    private ButtonPlay4DesignView buttonPlay4DesignView;

    private ErrorBoxView errorBoxView;

    public Cell4Design[][] getMatrix(){
        return getModel().getMatrix();
    }
    public Board4DesignView(Stage primaryStage, Board4DesignViewModel board4DesignViewModel){
        super(primaryStage, board4DesignViewModel);
    }

    public void createGrid () {

        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get();
                    double availableHeight = heightProperty().get() - getHeaderBox().heightProperty().get();
                    double aspectRatio = (double) getGRID_WIDTH() / getGRID_HEIGHT();

                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth / getGRID_WIDTH()) * getGRID_WIDTH();
                },
                widthProperty(),
                heightProperty(),
                getHeaderBox().heightProperty());

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(getGRID_WIDTH()).multiply(getGRID_HEIGHT());
        DoubleBinding cellSizeBinding = gridWidthBinding.divide(getGRID_WIDTH());

        Grid4DesignView grid4DesignView = new Grid4DesignView(getModel().getGridViewModel(), gridWidthBinding, gridHeightBinding);

        grid4DesignView.minHeightProperty().bind(gridHeightBinding);
        grid4DesignView.minWidthProperty().bind(gridWidthBinding);
        grid4DesignView.maxHeightProperty().bind(gridHeightBinding);
        grid4DesignView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(grid4DesignView);
        createBoiteAOutils(cellSizeBinding);
    }


    private void createBoiteAOutils(DoubleBinding cellsize){
        ToolsBoxView boiteAOutilsView = new ToolsBoxView(cellsize, getModel().getToolsBoxViewModel());
        boiteAOutilsView.setAlignment(Pos.CENTER_LEFT);
        setLeft(boiteAOutilsView);
    }

    public void createCompteur(){
        errorBoxView = new ErrorBoxView(getModel().getErrorBoxViewModel());

        headerLabel = new Label();
        headerLabel.textProperty().bind(getModel().filledCellsCountProperty()
              .asString("Number of filled cells: %d of " + getModel().maxFilledCells()));
       headerLabel.getStyleClass().add("header");



       VBox headerAndErrorBox = new VBox();
       headerAndErrorBox.setAlignment(Pos.CENTER);
      headerAndErrorBox.getChildren().add(headerLabel);
      headerAndErrorBox.getChildren().add(errorBoxView);

     getHeaderBox().getChildren().addAll(headerAndErrorBox);
        getHeaderBox().setAlignment(Pos.CENTER);


        setTop(getHeaderBox());
        VBox topBox = new VBox();
        topBox.getChildren().addAll(getMenuBar(),getHeaderBox());
        setTop(topBox);

    }

    @Override
    public void createMenu() {
        MenueView menueView = new MenueView(this);
        menueView.showConfirmationDialog1();
        setMenuBar(menueView.createMenuBar());
        setTop(getMenuBar());
    }


    @Override
    public void createButton() {
        buttonPlay4DesignView = new ButtonPlay4DesignView(getModel());
        buttonPlay4DesignView.setAlignment(Pos.TOP_CENTER);
        buttonPlay4DesignView.setPadding(new Insets(16, 16, 16, 16));
        buttonPlay4DesignView.setPrefHeight(40);
        setBottom(buttonPlay4DesignView);
    }

}

