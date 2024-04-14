package sokoban;

import javafx.application.Application;
import javafx.stage.Stage;
import sokoban.model.Board4Play;
import sokoban.view.Board4DesignView;

import sokoban.model.Board4Design;
import sokoban.view.Board4PlayView;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;


public class SokobanApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // TODO: basez vous sur l'exercice de la grille comme point de départ pour votre projet
        Board4Design board4Design = new Board4Design();
        Board4DesignViewModel vm = new Board4DesignViewModel(board4Design);
        new Board4DesignView(primaryStage, vm);
//        Board4Play board4Play = new Board4Play();
//        Board4PlayViewModel board4PlayViewModel = new Board4PlayViewModel(board4Play);
//        new Board4PlayView(primaryStage, board4PlayViewModel);

    }

    public static void main(String[] args) {
        launch(args);
    }
}