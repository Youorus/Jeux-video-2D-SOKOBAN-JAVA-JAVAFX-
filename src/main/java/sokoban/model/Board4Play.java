package sokoban.model;

import javafx.beans.property.ReadOnlyObjectProperty;

public class Board4Play extends Board{

    public Grid4Play getGrid4Play() {
        return grid4Play;
    }

    private Grid4Play grid4Play = new Grid4Play();

//    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
//        return grid4Play.valueProperty(line, col);
//    }
    public Board4Play(Board4Design board4Design){
        this.grid4Play.copyElements(board4Design.getGrid());
    }
}
