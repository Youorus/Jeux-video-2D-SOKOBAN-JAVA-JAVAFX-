    package sokoban.viewmodel;

    import javafx.beans.property.BooleanProperty;
    import javafx.collections.ObservableList;
    import sokoban.model.*;
    //import sokoban.model.CellValue;


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
            return board4Design.getGrid4Design().getCellsElements(getLine(),getCol());
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