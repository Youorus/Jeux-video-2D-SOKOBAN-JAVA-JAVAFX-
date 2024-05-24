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

    private Grid4PlayViewModel grid4PlayViewModel;
    public Grid4PlayView(Grid4PlayViewModel grid4PlayViewModel, DoubleBinding gridWidth, DoubleBinding gridHeight){
        setPadding(new Insets(PADDING));

        int height = grid4PlayViewModel.getBoard4Play().getGrid4Play().getGridHeight();

        int width = grid4PlayViewModel.getBoard4Play().getGrid4Play().getGridWidth();

        DoubleBinding cellSize = Bindings.createDoubleBinding(() ->
                        Math.min(
                                gridWidth.subtract(PADDING * 2).get() / width,
                                gridHeight.subtract(PADDING * 2).get() /height),
                gridWidth, gridHeight);


        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Cell4PlayView cell4PlayView = new Cell4PlayView(grid4PlayViewModel.getCellViewModel(i, j), cellSize);
                add(cell4PlayView, j, i);
            }
        }
    }
}
