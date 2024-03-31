package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;
import sokoban.viewmodel.BoardViewModel;
import sokoban.viewmodel.Grid4PlayViewModel;

import java.util.Objects;

public class Board4PlayView extends BoardView<Board4PlayViewModel> {

    private Board4PlayViewModel board4PlayViewModel;

    public Board4PlayView(Stage secondaryStage, Board4PlayViewModel board4PlayViewModel){
        super(secondaryStage, board4PlayViewModel);
    }


//    private void start(Stage stage){
//        configMainComponements(stage);
//
//        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
//        String cssFile = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
//        scene.getStylesheets().add(cssFile);
//        stage.setScene(scene);
//        stage.show();
//        stage.setMinHeight(stage.getHeight());
//        stage.setMinWidth(stage.getWidth());
//    }

//    private void configMainComponements(Stage stage){
//        stage.setTitle("Sokoban");
//        //createMenue();
//        createGrid();
//       // createCompteur();
//
//    }

    public void createGrid () {

        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get();
                    double aspectRatio = (double) getGRID_WIDTH() / getGRID_HEIGHT();
                    double availableHeight = heightProperty().get() - getHeaderBox().heightProperty().get();
                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth / getGRID_WIDTH()) * getGRID_WIDTH();
                },
                widthProperty(),
                heightProperty());
                //headerBox.heightProperty());

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(getGRID_WIDTH()).multiply(getGRID_HEIGHT());

        Grid4PlayView grid4PlayView = new Grid4PlayView(getModel().getGrid4PlayViewModel(),gridWidthBinding, gridHeightBinding);


        grid4PlayView.minHeightProperty().bind(gridHeightBinding);
        grid4PlayView.minWidthProperty().bind(gridWidthBinding);
        grid4PlayView.maxHeightProperty().bind(gridHeightBinding);
        grid4PlayView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(grid4PlayView);


        // Grille carrée

    }


    public void createCompteur(){

     //  setHeaderLabel(new Label("Score"));


//
//        VBox headerAndErrorBox = new VBox();
//        headerAndErrorBox.setAlignment(Pos.CENTER);
//        headerAndErrorBox.getChildren().addAll(headerLabel, errorBoxView);
//
//        getHeaderBox().getChildren().addAll(headerAndErrorBox);
//        getHeaderBox().setAlignment(Pos.CENTER);
//        setTop(getHeaderBox());
//        VBox topBox = new VBox();
//        topBox.getChildren().addAll(menuBar,getHeaderBox());
//        setTop(topBox);

    }

    @Override
    public void createMenu() {

    }

    @Override
    public void createButton() {
        //bouton finish
    }

}
