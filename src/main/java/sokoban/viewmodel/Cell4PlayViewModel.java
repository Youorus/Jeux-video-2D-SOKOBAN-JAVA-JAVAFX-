package sokoban.viewmodel;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import sokoban.model.Board4Design;
import sokoban.model.Board4Play;
import sokoban.model.Element;
import sokoban.model.Grid4Design;

public class Cell4PlayViewModel extends CellViewModel {

    private final Board4Play board4Play;

//    public ReadOnlyObjectProperty<Element> valueProperty() {
//        return board4Play.valueProperty(getLine(), getCol());
//    }

    Cell4PlayViewModel(int line, int col, Board4Play board4Play){
       super(line, col);
        this.board4Play = board4Play;
    }

    public ObservableList<Element> getCellsElements() {
        return board4Play.getGrid4Play().getCellsElements(getLine(),getCol());
    }

}
