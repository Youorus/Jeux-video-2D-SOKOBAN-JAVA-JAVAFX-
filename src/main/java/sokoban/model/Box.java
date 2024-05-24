package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Objects;

public class Box extends Element {
    private final int boxNumber; // Numéro unique de la boîte
    private static int boxCounter = 0;

    public Box() {
        boxCounter++;
        boxNumber = boxCounter; // Affecte le numéro unique à la boîte lors de sa création
    }

    // Méthode pour récupérer le numéro unique de la boîte
    public int getBoxNumber() {
        return boxNumber;
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
