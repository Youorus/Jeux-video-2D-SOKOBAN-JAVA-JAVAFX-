    package sokoban.viewmodel;

    import javafx.beans.binding.BooleanBinding;
    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.ReadOnlyObjectProperty;
    import javafx.beans.property.SimpleDoubleProperty;
    import javafx.collections.ObservableList;
    import javafx.collections.ObservableSet;
    import sokoban.model.*;
    //import sokoban.model.CellValue;

    import java.util.Set;


    public class Cell4DesignViewModel extends CellViewModel {
        private final Board4Design board4Design;
        private final ToolsBoxViewModel toolsBoxViewModel;

        private Cell4Design cell4Design;


        public Cell4DesignViewModel(int line, int col, Board4Design board4Design) {
            super(line, col);
            this.board4Design = board4Design;
            this.toolsBoxViewModel = new ToolsBoxViewModel(board4Design.getToolsBox());
        }

        public ObservableList<Element> getCellsElements() {
            return board4Design.getGrid().getCellsElements(getLine(),getCol());
        }

        public BooleanProperty gridEditedProperty() {
            return board4Design.gridEditedProperty();
        }


        public ToolsBoxViewModel getToolsBoxViewModel() {
            return toolsBoxViewModel;
        }


        public void add(Element element) {
            board4Design.add(getLine(), getCol(), element);
        }

    }