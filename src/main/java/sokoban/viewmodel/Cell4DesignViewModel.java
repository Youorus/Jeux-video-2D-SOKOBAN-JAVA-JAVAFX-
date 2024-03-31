    package sokoban.viewmodel;

    import javafx.beans.binding.BooleanBinding;
    import javafx.beans.property.ReadOnlyObjectProperty;
    import javafx.beans.property.SimpleDoubleProperty;
    import sokoban.model.*;
    //import sokoban.model.CellValue;

    import java.util.Set;


    public class Cell4DesignViewModel extends CellViewModel {
        private final Board4Design board4Design;
        private final ToolsBoxViewModel toolsBoxViewModel;


        public Cell4DesignViewModel(int line, int col, Board4Design board4Design) {
            super(line, col);
            this.board4Design = board4Design;
            this.toolsBoxViewModel = new ToolsBoxViewModel(board4Design.getToolsBox());
        }

        public ToolsBoxViewModel getToolsBoxViewModel() {
            return toolsBoxViewModel;
        }

        public ReadOnlyObjectProperty<Element> valueProperty() {
            return board4Design.valueProperty(getLine(), getCol());
        }

        public void add(Element element) {
            board4Design.add(getLine(), getCol(), element);
        }

    }