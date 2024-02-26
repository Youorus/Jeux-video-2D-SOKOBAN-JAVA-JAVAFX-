package sokoban.viewmodel;

import javafx.beans.binding.BooleanBinding;
import sokoban.model.Menue;

public class MenueViewModel {
    private final Menue menue;
    public MenueViewModel(Menue menue){

        this.menue = menue;
    }

    public BooleanBinding isValidWidthProperty(){
        return menue.isValidWidthProperty();
    }
}
