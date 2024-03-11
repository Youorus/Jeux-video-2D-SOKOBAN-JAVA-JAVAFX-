package sokoban.model;

public class Box extends Element {
    @Override
    public CellValue getType() {
        return CellValue.box;
    }
    @Override
    public String getImage() {
        return "box.png";
    }
}
