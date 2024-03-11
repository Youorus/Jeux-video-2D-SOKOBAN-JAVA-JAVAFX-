package sokoban.model;

public class Wall extends Element {
    @Override
    public CellValue getType() {
        return CellValue.wall;
    }
    @Override
    public String getImage() {
        return "wall.png";
    }
}
