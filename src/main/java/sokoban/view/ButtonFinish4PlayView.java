package sokoban.view;

import javafx.scene.control.Button;
import sokoban.viewmodel.Board4PlayViewModel;

public class ButtonFinish4PlayView extends ButtonView<Board4PlayViewModel>{

    private final Button finishBtn = new Button("Finish");
    public ButtonFinish4PlayView(Board4PlayViewModel model) {
        super(model);
        getChildren().add(finishBtn);
    }
}
