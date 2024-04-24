package sokoban.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sokoban.model.Board4Play;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;

public class ButtonPlay4DesignView extends ButtonView<Board4DesignViewModel> {

    private final Stage secondaryStage = new Stage();

    private Alert alert;

    public Board4Play getBoard4Play() {
        return board4Play;
    }

    private Board4Play board4Play ;

    private Board4PlayViewModel board4PlayViewModel;
    private final Button playBtn = new Button("Play");

    private SimpleBooleanProperty errorListIsEmpty = new SimpleBooleanProperty();
    private final MenuView menuView;


    public ButtonPlay4DesignView(MenuView menuView, Board4DesignViewModel board4DesignViewModel) {
        super(board4DesignViewModel);
        this.menuView = menuView;
        disableProperty().bind(board4DesignViewModel.getErrorBoxViewModel().errorListProperty().emptyProperty().not());


        getChildren().add(playBtn);
        playBtnListener();
    }

    public void playBtnListener() {

        playBtn.setOnAction(actionEvent -> {

            board4Play = new Board4Play(getModel().getBoard4Design());
            board4PlayViewModel = new Board4PlayViewModel(board4Play);
            // faire le check de si la grille a été sauvegarder
            //si non ouvrir une boite de dialogue pour demander a la sauvegarder
            //lancer une instance de BoardPlay
            new Board4PlayView(secondaryStage, board4PlayViewModel);
        });


    }



}
