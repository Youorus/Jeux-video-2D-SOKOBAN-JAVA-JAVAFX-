package sokoban.model;

import java.util.Objects;

public class Wall extends Element {
    @Override
    public String getImage() {
        return "wall.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Wall;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Wall w) {
            return (w.getType() == ((Wall) o).getType());
        }
        return false;
    }


    @Override
    public String toString() {
        return "#";
    }
}
