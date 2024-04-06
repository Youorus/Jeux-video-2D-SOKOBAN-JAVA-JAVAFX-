package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import sokoban.model.Box;
import sokoban.model.Element;
import sokoban.model.Goal;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Board4PlayViewModel;
import sokoban.viewmodel.Grid4PlayViewModel;

public class Grid4PlayView extends GridView {
    private static final int PADDING = 20;
    private static final int GRID_WIDTH = Board4PlayViewModel.gridWidth();
    private static final int GRID_HEIGHT = Board4PlayViewModel.gridHeight();
    private Grid4PlayViewModel grid4PlayViewModel;
    public Grid4PlayView(Grid4PlayViewModel grid4PlayViewModel, DoubleBinding gridWidth, DoubleBinding gridHeight){
        setGridLinesVisible(true);
        setPadding(new Insets(PADDING));

        DoubleBinding cellSize = Bindings.createDoubleBinding(() ->
                        Math.min(
                                gridWidth.subtract(PADDING * 2).get() / GRID_WIDTH,
                                gridHeight.subtract(PADDING * 2).get() / GRID_HEIGHT),
                gridWidth, gridHeight);


        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                Cell4PlayView cell4PlayView = new Cell4PlayView(grid4PlayViewModel.getCellViewModel(i, j), cellSize);
                add(cell4PlayView, j, i);
            }
        }
    }
}
