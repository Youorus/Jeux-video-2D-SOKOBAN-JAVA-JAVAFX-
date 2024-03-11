package sokoban.model;

public class Player extends Element {
    @Override
    public CellValue getType() {
        return CellValue.player;
    }

    @Override
    public String getImage() {
        return "player.png";
    }
}
