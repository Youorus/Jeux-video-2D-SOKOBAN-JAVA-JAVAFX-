package sokoban.model;

public class Mushroom extends Element{
    @Override
    public String getImage() {
        return "mushroom.png";
    }

    @Override
    public ElementType getType() {
        return ElementType.Mushroom;
    }
}
