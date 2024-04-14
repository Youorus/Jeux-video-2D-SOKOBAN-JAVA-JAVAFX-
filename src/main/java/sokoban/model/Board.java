package sokoban.model;

abstract public class Board {

    public Wall getWall() {
        return wall;
    }

    public Ground getGround() {
        return ground;
    }

    private Ground ground = new Ground();

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

    private final Wall wall = new Wall();
}
