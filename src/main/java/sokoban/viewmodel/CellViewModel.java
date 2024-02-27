package sokoban.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import sokoban.model.Board;
import sokoban.model.CellValue;
import sokoban.model.Grid;


public class CellViewModel {
    private static final double DEFAULT_SCALE = 0.5;
    private static final double EPSILON = 1e-3;

    private final int line, col;
    private final Board board;

    private final SimpleDoubleProperty scale = new SimpleDoubleProperty(DEFAULT_SCALE);
    private final BooleanBinding mayIncrementScale = scale.lessThan(1 - EPSILON);
    private final BooleanBinding mayDecrementScale = scale.greaterThan(DEFAULT_SCALE + EPSILON);

    public CellViewModel(int line, int col, Board board) {
        this.line = line;
        this.col = col;
        this.board = board;
    }

    public ReadOnlyObjectProperty<CellValue> valueProperty() {
        return board.valueProperty(line, col);
    }

    public boolean isEmpty(int line, int col) {
        return board.isEmpty(line, col);
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

    public void decrementScale() {
        if (mayDecrementScale.get()) {
            scale.set(Math.max(DEFAULT_SCALE, scale.get() - 0.1));
        }
    }

    public void resetScale() {
        scale.set(DEFAULT_SCALE);
    }
}