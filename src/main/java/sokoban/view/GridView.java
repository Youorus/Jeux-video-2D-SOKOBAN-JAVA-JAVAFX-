package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import sokoban.viewmodel.BoardViewModel;
import sokoban.viewmodel.GridViewModel;

public class GridView extends GridPane {
    private static final int PADDING = 20;
    private static final int GRID_WIDTH = BoardViewModel.gridWidth();
    private static final int GRID_HEIGHT = BoardViewModel.gridHeight();

    public GridView(GridViewModel grilleViewModel, DoubleBinding gridWidth, DoubleBinding gridHeight) {
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
                CellView cellView = new CellView(grilleViewModel.getCellViewModel(i, j), cellSize);
                add(cellView, j, i);
            }
        }
    }
}
