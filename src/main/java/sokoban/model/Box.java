package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Objects;

public class Box extends Element {
    public static int getBoxCounter() {
        return boxCounter;
    }

    private static int boxCounter = 0;

    public Box() {

    }

    public static void increment(){
        boxCounter++;
    }
    public static void setBoxCounter(int count) {
        boxCounter = count;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return getType() == box.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public String toString() {
        return "$";
    }

}
