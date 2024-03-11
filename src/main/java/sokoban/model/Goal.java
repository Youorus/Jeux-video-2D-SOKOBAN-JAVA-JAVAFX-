package sokoban.model;

public class Goal extends Element {

    @Override
    public CellValue getType() {
        return CellValue.goal;
    }
    @Override
    public String getImage() {
        return "goal.png";
    }
}
