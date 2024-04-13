package sokoban.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import sokoban.viewmodel.Board4PlayViewModel;

public class ButtonFinish4PlayView extends ButtonView<Board4PlayViewModel>{

    private final Button finishBtn = new Button("Finish");
    public ButtonFinish4PlayView(Stage stage, Board4PlayViewModel model) {
        super(model);
        getChildren().add(finishBtn);

        finishBtn.setOnAction(event -> {
                stage.close();
        });
    }
}
