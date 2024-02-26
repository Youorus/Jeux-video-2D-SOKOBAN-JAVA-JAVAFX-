package sokoban.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sokoban.model.Menue;

public class MenueViewModel {
    private final IntegerProperty Width = new SimpleIntegerProperty();
    private final IntegerProperty Height = new SimpleIntegerProperty();
    private final BooleanProperty validWidth = new SimpleBooleanProperty(true);
    private final BooleanProperty validHeight = new SimpleBooleanProperty(true);
    private final Menue menue = new Menue();
    public MenueViewModel(){
        Width.addListener((obs, oldVal, newVal) -> validWidth.set(menue.isValidWidth(newVal.intValue())));
        Height.addListener((obs, oldVal, newVal) -> validHeight.set(menue.isValidHeight(newVal.intValue())));

    }

    public IntegerProperty widthProperty() {
        return Width;
    }

    public IntegerProperty heightProperty() {
        return Height;
    }

    public BooleanProperty isValidWidthProperty() {
        return validWidth;
    }

    public BooleanProperty isValidHeightProperty() {
        return validHeight;
    }

    public void validateWidth(){
        validWidth.set(Width.get() >= 10 && Width.get() <= 50 );
    }

    public void validateHeight(){
        validWidth.set(Height.get() >= 10 && Height.get() <= 50 );
    }

    public void updateModel() {
        if (validWidth.get() && validHeight.get()) {
            menue.setWidth(Width.get());
            menue.setHeight(Height.get());
            // Mise à jour du modèle avec les nouvelles dimensions valides
        }
    }




}
