package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

abstract public class Board {



    public Ground getGround() {
        return ground;
    }

    private final Ground ground = new Ground();
    public Wall getWall() {
        return wall;
    }
    private final Wall wall = new Wall();
    private final Player player = new Player();
    private final Box box = new Box();

    public Player getPlayer() {
        return player;
    }

    public Box getBox() {
        return box;
    }

    public Goal getGoal() {
        return goal;
    }

    private final Goal goal = new Goal();


}
