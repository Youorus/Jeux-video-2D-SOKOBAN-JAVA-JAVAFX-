    package sokoban.viewmodel;

    import javafx.beans.binding.BooleanBinding;
    import javafx.beans.property.ReadOnlyObjectProperty;
    import javafx.beans.property.SimpleDoubleProperty;
    import sokoban.model.*;
    //import sokoban.model.CellValue;

    import java.util.Set;


    public class Cell4DesignViewModel {

        private static final double DEFAULT_SCALE = 0.5;
        private static final double EPSILON = 1e-3;

        private final int line, col;
        private final Board4Design board4Design;

        public ToolsBoxViewModel getToolsBoxViewModel() {
            return toolsBoxViewModel;
        }

        private final ToolsBoxViewModel toolsBoxViewModel;

        private final SimpleDoubleProperty scale = new SimpleDoubleProperty(DEFAULT_SCALE);
        private final BooleanBinding mayIncrementScale = scale.lessThan(1 - EPSILON);
        private final BooleanBinding mayDecrementScale = scale.greaterThan(DEFAULT_SCALE + EPSILON);

        public Cell4DesignViewModel(int line, int col, Board4Design board4Design) {
            this.line = line;
            this.col = col;
            this.board4Design = board4Design;
            this.toolsBoxViewModel = new ToolsBoxViewModel(board4Design.getToolsBox());
        }

        public ReadOnlyObjectProperty<Element> valueProperty() {
            return board4Design.valueProperty(line, col);
        }

        public boolean isEmpty(int line, int col) {
            return board4Design.isEmpty(line, col);
        }

        public SimpleDoubleProperty scaleProperty() {
            return scale;
        }

        public BooleanBinding mayIncrementScaleProperty() {
            return mayIncrementScale;
        }

        public BooleanBinding mayDecrementScaleProperty() {
            return mayDecrementScale;
        }

        public void incrementScale() {
            if (mayIncrementScale.get()) {
                scale.set(Math.min(1, scale.get() + 0.1));
            }
        }

        public Set<Element> getCellsElements() {
            return board4Design.getGrid().getCellsElements(line, col);
        }

        public void add(Element element) {
            board4Design.add(line, col, element);
        }

        public void decrementScale() {
            if (mayDecrementScale.get()) {
                scale.set(Math.max(DEFAULT_SCALE, scale.get() - 0.1));
            }
        }

        public void resetScale() {
            scale.set(DEFAULT_SCALE);
        }
    }