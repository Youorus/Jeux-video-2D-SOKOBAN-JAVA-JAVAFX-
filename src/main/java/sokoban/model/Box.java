package sokoban.model;

import java.util.Objects;

public class Box extends Element {

    static int boxNumber = 0;

    public static int getBoxNumber() {
        return boxNumber;
    }

    public Box() {
        boxNumber += 1;
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
