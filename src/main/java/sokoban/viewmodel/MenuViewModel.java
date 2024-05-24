package sokoban.viewmodel;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sokoban.model.Board4Play;
import sokoban.view.Board4DesignView;

public class MenuViewModel {

    public void setWidth(int width) {
        this.width.set(width);
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    private final IntegerProperty width = new SimpleIntegerProperty();
    private final IntegerProperty height = new SimpleIntegerProperty();
    private final BooleanProperty validWidth = new SimpleBooleanProperty(true);
    private final BooleanProperty validHeight = new SimpleBooleanProperty(true);

    private final Board4DesignView board4DesignView;
    public MenuViewModel(Board4DesignView board4DesignView){
        this.board4DesignView = board4DesignView;
        width.addListener((obs, oldVal, newVal) -> validWidth.set(isValidWidth(newVal.intValue())));
        height.addListener((obs, oldVal, newVal) -> validHeight.set(isValidHeight(newVal.intValue())));

    }


    public int getWidth() {
        return width.get();
    }

    public int getHeight() {
        return height.get();
    }


    public void validateWidth(String newValue){
        try {
            int newWidth = Integer.parseInt(newValue);
            isValidWidthProperty().set(newWidth >= 10 && newWidth <= 50);
        } catch (NumberFormatException e) {
            isValidWidthProperty().set(false);
        }

    }

    public void validateHeight(String newValue){
        try {
            int newWidth = Integer.parseInt(newValue);
            isValidHeightProperty().set(newWidth >= 10 && newWidth <= 50);
        } catch (NumberFormatException e) {
            isValidHeightProperty().set(false);
        }
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public BooleanProperty isValidWidthProperty() {
        return validWidth;
    }

    public BooleanProperty isValidHeightProperty() {
        return validHeight;
    }


    public void updateModel() {
        if (validWidth.get() && validHeight.get()) {
            this.board4DesignView.getViewModel().getBoard4Design().getGrid4Design().reset(height.get(),width.get());
            this.board4DesignView.createGrid();
        }
    }

    private boolean isValidWidth(int Width){
        return Width >= 10 && Width <= 50;
    }

    private boolean isValidHeight(int Height){
        return Height >= 10 && Height <= 50;
    }




}
