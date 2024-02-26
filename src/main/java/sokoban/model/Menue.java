package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Menue {
    private int Width;
    private int Height;

    public Menue(){

    }

    public int getWidth() {
        return Width;
    }
    public void setWidth(int width){
        this.Width= width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        this.Height = height;
    }
    public boolean isValidWidth(int Width){
        return Width >= 10 && Width <= 50;
    }

    public boolean isValidHeight(int Height){
        return Height >= 10 && Height <= 50;
    }
}
