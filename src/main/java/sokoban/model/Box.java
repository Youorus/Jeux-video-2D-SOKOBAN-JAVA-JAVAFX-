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
        boxCounter++;
    }


    public static int boxIncrement(){
        return boxCounter++;
    }
    public static void resetBoxCounter() {
        boxCounter = 0;
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
