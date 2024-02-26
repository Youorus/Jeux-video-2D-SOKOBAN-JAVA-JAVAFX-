package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Menue {
    private  IntegerProperty Width = new SimpleIntegerProperty();

    public Menue(){

    }

//    private boolean isValidWidth(){
//
//    }
//
//    public BooleanBinding isValidWidthProperty(){
//        return Bindings.createBooleanBinding(this::isValidWidth, Width);
//    }
}
