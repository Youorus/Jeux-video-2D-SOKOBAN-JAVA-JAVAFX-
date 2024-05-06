package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Objects;

public class Box extends Element {

    public boolean isIsNumerote() {
        return isNumerote.get();
    }

    public BooleanProperty isNumeroteProperty() {
        return isNumerote;
    }

    public void setIsNumerote(boolean isNumerote) {
        this.isNumerote.set(isNumerote);
    }

    private final BooleanProperty isNumerote = new SimpleBooleanProperty(false);
    static int boxNumber = 0;

    public static int getBoxNumber() {
        return boxNumber;
    }

    public static void increment(){
        boxNumber += 1;
    }

    public Box() {
    }

    @Override
    public String getImage() {
        return "box.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Box;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Box b) {
            return (b.getType() == ((Box) o).getType());
        }
        return false;
    }

    @Override
    public String toString() {
        return "$";
    }

}
