package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;

public class Cell {


    public boolean isHasPlayer() {
        return hasPlayer.get();
    }

    public SimpleBooleanProperty hasPlayerProperty() {
        return hasPlayer;
    }

    private final SimpleBooleanProperty hasPlayer = new SimpleBooleanProperty();
    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer.set(hasPlayer);
    }



    public ObservableList<Element> getCellsElements() {
        return cellsElements;
    }
    private final ObservableList<Element> cellsElements = FXCollections.observableArrayList();


public boolean isEmpty(){
    return cellsElements.isEmpty();
}

}
