package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.Grid4DesignViewModel;

public class Grid4DesignView extends GridView {
    private static final int PADDING = 20;
    private static final int GRID_WIDTH = Board4DesignViewModel.gridWidth();
    private static final int GRID_HEIGHT = Board4DesignViewModel.gridHeight();

    public Grid4DesignView(Grid4DesignViewModel grid4DesignViewModel, DoubleBinding gridWidth, DoubleBinding gridHeight) {
        // Pour visualiser les limites de la grille
        setGridLinesVisible(true);
        setPadding(new Insets(PADDING));

        DoubleBinding cellSize = Bindings.createDoubleBinding(() ->
                        Math.min(
                                gridWidth.subtract(PADDING * 2).get() / GRID_WIDTH,
                                gridHeight.subtract(PADDING * 2).get() / GRID_HEIGHT),
                gridWidth, gridHeight);


        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                Cell4DesignView cell4DesignView = new Cell4DesignView(grid4DesignViewModel.getCellViewModel(i, j), cellSize);
                add(cell4DesignView, j, i);
            }
        }
    }
}
