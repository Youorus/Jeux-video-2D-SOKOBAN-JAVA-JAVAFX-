package sokoban.model;

import java.util.Objects;

public class Player extends Element {


    @Override
    public String getImage() {
        return "player.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Player;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player p) {
            return (p.getType() == ((Player) o).getType());
        }
        return false;
    }


    @Override
    public String toString() {
        return "@";
    }
}
