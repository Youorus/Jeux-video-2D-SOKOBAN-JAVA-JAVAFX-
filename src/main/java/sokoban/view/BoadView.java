package sokoban.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.BoardViewModel;

public class BoadView extends BorderPane {
    //mise en page
    private final BoardViewModel boardViewModel;
    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;


    //pour le compteur
    private final Label headerLabel = new Label("");
    private final HBox headerBox = new HBox();

    // pour la boite a outils
    private final VBox leftBox = new VBox();
    public BoadView(Stage primaryStage, BoardViewModel boardViewModel){
        this.boardViewModel = boardViewModel;
        start(primaryStage);

    }
    private void start(Stage stage){
        configMainComponements(stage);

        //Mise en place de la scène et affichage de la fenêtre
        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        stage.show();
    }

    private void configMainComponements(Stage stage){
        stage.setTitle("Sokoban");
        createGrid();
        createBoiteAOutils();
        createCompteur();
        createMenue();
    }

    private void createGrid (){
       // GrilleView grilleView = new GrilleView(boardViewModel.getGrilleViewModel(), gridWidth);


        // Grille carrée

    }
    private void createBoiteAOutils(){

    }
    private void createCompteur(){

    }
    private void createMenue(){

    }


}
