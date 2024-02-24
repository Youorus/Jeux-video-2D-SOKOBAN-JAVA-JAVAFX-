package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import sokoban.viewmodel.GridViewModel;

public class GridView extends GridPane {
    private static final int PADDING = 20;
    private static final int GRID_WIDTH = 15;
    private static final int GRID_HEIGHT = 10;

    public GridView(GridViewModel grilleViewModel, DoubleBinding gridWidth) {
        // Pour visualiser les limites de la grille
        setGridLinesVisible(false);
        setPadding(new Insets(PADDING));

        DoubleBinding cellWidth = gridWidth.subtract(PADDING * 2).divide(GRID_WIDTH);

        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                CellView cellView = new CellView(grilleViewModel.getCellViewModel(i, j), cellWidth);
                add(cellView, j, i);
            }
        }
    }
}