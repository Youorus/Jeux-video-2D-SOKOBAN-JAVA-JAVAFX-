package sokoban;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sokoban.model.Grid;
import sokoban.view.GridView;
import sokoban.view.GridView;
import sokoban.viewmodel.GridViewModel;
import sokoban.viewmodel.GridViewModel;

public class SokobanApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Grid grid = new Grid();
        GridViewModel viewModel = new GridViewModel(grid);

        StackPane root = new StackPane();
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 600);

        DoubleBinding gridWidth = root.widthProperty().subtract(root.getPadding().getLeft() + root.getPadding().getRight());

        GridView gridView = new GridView(viewModel, gridWidth);

        root.getChildren().add(gridView);

        primaryStage.setTitle("Sokoban");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}