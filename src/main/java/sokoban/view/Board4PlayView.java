package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;
import sokoban.viewmodel.Grid4PlayViewModel;

import java.util.Objects;

public class Board4PlayView extends BorderPane {

    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;

    private final HBox headerBox = new HBox();

    private static final int GRID_WIDTH = Board4PlayViewModel.gridWidth();

    private static final int GRID_HEIGHT = Board4PlayViewModel.gridHeight();

    private final Board4PlayViewModel board4PlayViewModel;
    public Board4PlayView(Stage secondaryStage, Board4PlayViewModel board4PlayViewModel){
        this.board4PlayViewModel = board4PlayViewModel;
        start(secondaryStage);
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
        //createMenue();
        createGrid();
       // createCompteur();

    }

    private void createGrid () {

        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get();
                    double aspectRatio = (double) GRID_WIDTH / GRID_HEIGHT;
                    double availableHeight = heightProperty().get() - headerBox.heightProperty().get();
                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth / GRID_WIDTH) * GRID_WIDTH;
                },
                widthProperty(),
                heightProperty());
                //headerBox.heightProperty());

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(GRID_WIDTH).multiply(GRID_HEIGHT);

        Grid4PlayView grid4PlayView = new Grid4PlayView(board4PlayViewModel.getGrid4PlayViewModel(),gridWidthBinding, gridHeightBinding);


        grid4PlayView.minHeightProperty().bind(gridHeightBinding);
        grid4PlayView.minWidthProperty().bind(gridWidthBinding);
        grid4PlayView.maxHeightProperty().bind(gridHeightBinding);
        grid4PlayView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(grid4PlayView);


        // Grille carrée

    }

}
