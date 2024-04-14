package sokoban.view;

import javafx.scene.layout.HBox;
import sokoban.viewmodel.BoardViewModel;

public abstract class ButtonView<T extends BoardViewModel> extends HBox {

    public T getModel() {
        return model;
    }

    private T model;
    public ButtonView(T model){
        this.model = model;
    }
}
