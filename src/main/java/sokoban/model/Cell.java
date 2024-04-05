package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;

public class Cell {
//    public Element getValue() {
//        return value.get();
//    }



    public ObservableList<Element> getCellsElements() {
        return cellsElements;
    }

    private ObservableList<Element> cellsElements = FXCollections.observableArrayList();


public boolean isEmpty(){
    return cellsElements.isEmpty();
}

    public void add(Element element){
        cellsElements.add(element);
    }

    public void remove(Element element){
        cellsElements.remove(element);
    }
//    public void setValue(Element value) {
//        this.value.set(value);
//    }


//    public boolean isEmpty() {
//        return value.get() instanceof Ground;
//    }

//    public ReadOnlyObjectProperty<Element> valueProperty() {
//        return value;
//    }

  //  private final ObjectProperty<Element> value = new SimpleObjectProperty<>(new Ground());
}
