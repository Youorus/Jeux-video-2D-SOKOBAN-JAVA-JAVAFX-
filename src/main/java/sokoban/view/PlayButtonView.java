package sokoban.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sokoban.model.Board4Play;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;

public class PlayButtonView extends HBox {

    private final Stage secondaryStage = new Stage();

    private Board4Play board4Play = new Board4Play();

    private Board4PlayViewModel board4PlayViewModel = new Board4PlayViewModel(board4Play);
    private final Button playBtn = new Button("Play");

    private SimpleBooleanProperty errorListIsEmpty = new SimpleBooleanProperty();
    private final Board4DesignViewModel board4DesignViewModel;


    public PlayButtonView(Board4DesignViewModel board4DesignViewModel) {
        this.board4DesignViewModel = board4DesignViewModel;

        disableProperty().bind(board4DesignViewModel.getErrorBoxViewModel().errorListProperty().emptyProperty().not());


        getChildren().add(playBtn);
        playBtnListener();
    }

    public void playBtnListener() {
        playBtn.setOnAction(actionEvent -> {
            // faire le check de si la grille a été sauvegarder
            //si non ouvrir une boite de dialogue pour demander a la sauvegarder
            //lancer une instance de BoardPlay
            new Board4PlayView(secondaryStage, board4PlayViewModel);
        });
    }

}
