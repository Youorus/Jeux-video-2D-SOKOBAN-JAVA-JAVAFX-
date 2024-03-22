package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;

    public Set<Element> getGridArrays() {
        return gridArrays;
    }

    //les rendre non static
    private Set<Element> gridArrays = new HashSet<>();
    private final Cell[][] matrix;
    private final LongBinding filledCellsCount;

    public Grid() {
        matrix = new Cell[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Cell();// je construis une nouvelle cell
                matrix[i][j].setValue(new Ground()); // Initialisation avec Ground
            }
        }

        filledCellsCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isEmpty())
                .count());
    }

    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static int getGridHeight() {
        return GRID_HEIGHT;
    }

    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
        return matrix[line][col].valueProperty();
    }

    public boolean hasPlayer() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (matrix[i][j].getCellsElements().contains(new Player())) {
                    return true; // Un joueur a été trouvé
                }
            }
        }
        return false; // Aucun joueur n'a été trouvé
    }


    public int[] getPlayerPosition() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (matrix[i][j].getCellsElements().contains(new Player())) {
                    return new int[]{i, j}; // Retourne les coordonnées du joueur
                }
            }
        }
        // Si aucun joueur n'est trouvé, retourne un tableau {-1, -1}
        return new int[]{-1, -1};
    }

    public void add(int line, int col, Element element) {
        matrix[line][col].setValue(element);
        matrix[line][col].add(element);
        System.out.println(matrix[line][col].getCellsElements());
        gridArrays.add(element);
        filledCellsCount.invalidate();
    }

    public void remove(int line, int col) {
        Element removedElement = matrix[line][col].getValue();
        matrix[line][col].setValue(new Ground()); // Remplace l'élément par Ground
        matrix[line][col].getCellsElements().remove(removedElement); // Retire l'élément de la liste des éléments de la cellule
        gridArrays.remove(removedElement); // Retire l'élément de l'ensemble des éléments de la grille
        filledCellsCount.invalidate(); // Indique que le nombre de cellules remplies a changé
    }

    public Set<Element> getCellsElements(int line, int col) {
        return matrix[line][col].getCellsElements();
    }




    public LongBinding filledCellsCountProperty() {
        return filledCellsCount;
    }


    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }

    public Element getValue(int line, int col) {
        return matrix[line][col].getValue();
    }
}
