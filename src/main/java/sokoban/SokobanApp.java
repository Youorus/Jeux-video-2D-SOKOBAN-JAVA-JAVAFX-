package sokoban;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
<<<<<<< HEAD
import sokoban.model.Grid;
import sokoban.view.BoardView;
import sokoban.view.GridView;
import sokoban.view.GridView;
import sokoban.viewmodel.GridViewModel;
import sokoban.viewmodel.GridViewModel;

import sokoban.model.Board;
import sokoban.view.BoardView;
import sokoban.viewmodel.BoardViewModel;


public class SokobanApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // TODO: basez vous sur l'exercice de la grille comme point de départ pour votre projet
        Board board = new Board();
        BoardViewModel vm = new BoardViewModel(board);
        new BoardView(primaryStage, vm);

    }

    public static void main(String[] args) {
        launch(args);
    }
}