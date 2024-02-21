package sokoban.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.BoardViewModel;

import java.util.Objects;

public class BoadView extends BorderPane {
    //mise en page
    private final BoardViewModel boardViewModel;
    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;
    private MenuBar menuBar;

    private VBox errorBox;

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
        String cssFile = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        scene.getStylesheets().add(cssFile);

        stage.setScene(scene);

        stage.show();
    }

    private void configMainComponements(Stage stage){
        stage.setTitle("Sokoban");
        createMenue();
        createGrid();
        createBoiteAOutils();
        createCompteur();

    }

    private void createGrid (){
       // GrilleView grilleView = new GrilleView(boardViewModel.getGrilleViewModel(), gridWidth);


        // Grille carrée

    }
    private void createBoiteAOutils(){

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
