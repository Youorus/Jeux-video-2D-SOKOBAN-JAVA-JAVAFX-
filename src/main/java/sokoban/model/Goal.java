package sokoban.model;

import java.util.Objects;

public class Goal extends Element {

    @Override
    public String getImage() {
        return "goal.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Goal;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Goal g) {
            return (g.getType() == ((Goal) o).getType());
        }
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }
}
