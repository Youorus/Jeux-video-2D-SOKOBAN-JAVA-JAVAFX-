package sokoban.model;

public class Ground extends Element {

    @Override
    public CellValue getType() {
        return CellValue.ground;
    }
    @Override
    public String getImage() {
        return "ground.png";
    }
}
