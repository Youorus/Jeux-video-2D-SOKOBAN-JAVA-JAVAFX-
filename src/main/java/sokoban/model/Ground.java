package sokoban.model;

import java.util.Objects;

public class Ground extends Element {


    @Override
    public String getImage() {
        return "ground.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Ground;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Ground g) {
            return (g.getType() == ((Ground) o).getType());
        }
        return false;
    }

    public void Validate(Element element){

    }

    @Override
    public String toString() {
        return "x";
    }
}
