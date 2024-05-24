package sokoban.view;

import javafx.event.Event;
import javafx.scene.control.Button;
import sokoban.viewmodel.Board4PlayViewModel;

public class ButtonMushroom4PlayView extends ButtonView<Board4PlayViewModel> {

    private final Button mushroomBtn = new Button("Show Mushroom");

    public ButtonMushroom4PlayView(Board4PlayViewModel model) {
        super(model);
        mushroomBtn.setFocusTraversable(false);
        getChildren().add(mushroomBtn);



        mushroomBtn.setOnAction(event -> {
            boolean mushroomVisibility = getModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().isShowMushroom();
            getModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().setShowMushroom(!mushroomVisibility);
            if (!mushroomVisibility) {
                mushroomBtn.setText("Hide mushroom");
                getModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().setMoveCount(getModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().getMoveCount() + 10);
            } else {
                mushroomBtn.setText("Show mushroom");
            }
        });
    }



}

