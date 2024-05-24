package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import sokoban.viewmodel.Grid4DesignViewModel;

public class Grid4DesignView extends GridView {
    private static final int PADDING = 20;

    public Grid4DesignView(Grid4DesignViewModel grid4DesignViewModel, DoubleBinding gridWidth, DoubleBinding gridHeight) {
        // Pour visualiser les limites de la grille
        setPadding(new Insets(PADDING));

        int height = grid4DesignViewModel.getBoard4Design().getGrid4Design().getGridHeight();

        int width = grid4DesignViewModel.getBoard4Design().getGrid4Design().getGridWidth();



        DoubleBinding cellSize = Bindings.createDoubleBinding(() ->
                        Math.min(
                                gridWidth.subtract(PADDING * 2).get() / width ,
                                gridHeight.subtract(PADDING * 2).get() / height),
                gridWidth, gridHeight);


        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Cell4DesignView cell4DesignView = new Cell4DesignView(grid4DesignViewModel.getCellViewModel(i, j), cellSize);
                add(cell4DesignView, j, i);
            }
        }
    }
}
