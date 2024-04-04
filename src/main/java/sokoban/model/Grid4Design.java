package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grid4Design extends Grid<Cell4Design> {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;



    public Set<Element> getGridArrays() {
        return gridArrays;
    }

    //les rendre non static
    private Set<Element> gridArrays = new HashSet<>();


    private final LongBinding filledCellsCount;


    public Grid4Design() {
        super(GRID_HEIGHT, GRID_WIDTH);
        filledCellsCount = Bindings.createLongBinding(() -> Arrays
                .stream(getMatrix())
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
        return getMatrix()[line][col].valueProperty();
    }

    public boolean hasPlayer() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Player())) {
                    return true; // Un joueur a été trouvé
                }
            }
        }
        return false; // Aucun joueur n'a été trouvé
    }

    public boolean hasGoal() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Goal())) {
                    return true; // Un joueur a été trouvé
                }
            }
        }
        return false; // Aucun joueur n'a été trouvé
    }

    public boolean goalTargetEquals() {
        int goalCount = 0;
        int boxCount = 0;

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Cell4Design cell4Design = getMatrix()[i][j];
                goalCount += (int) cell4Design.getCellsElements().stream()
                        .filter(element -> element instanceof Goal)
                        .count();
                boxCount += (int) cell4Design.getCellsElements().stream()
                        .filter(element -> element instanceof Box)
                        .count();
            }
        }

        return goalCount == boxCount;
    }


    public boolean hasBox() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Box())) {
                    return true; // Une Box  a été trouvé
                }
            }
        }
        return false; // Aucun Box n'a été trouvé
    }


    public int[] getPlayerPosition() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Player())) {
                    return new int[]{i, j}; // Retourne les coordonnées du joueur
                }
            }
        }
        // Si aucun joueur n'est trouvé, retourne un tableau {-1, -1}
        return new int[]{-1, -1};
    }

    public void add(int line, int col, Element element) {
        getMatrix()[line][col].setValue(element);
        getMatrix()[line][col].add(element);
        filledCellsCount.invalidate();
    }


    public void remove(int line, int col) {
        Element removedElement = getMatrix()[line][col].getValue();
        getMatrix()[line][col].setValue(new Ground()); // Remplace l'élément par Ground
        getMatrix()[line][col].getCellsElements().remove(removedElement); // Retire l'élément de la liste des éléments de la cellule
        gridArrays.remove(removedElement); // Retire l'élément de l'ensemble des éléments de la grille
        filledCellsCount.invalidate(); // Indique que le nombre de cellules remplies a changé
    }




    public LongBinding filledCellsCountProperty() {
        return filledCellsCount;
    }


    public boolean isEmpty(int line, int col) {
        return getMatrix()[line][col].isEmpty();
    }



    @Override
    public Cell4Design[][] createMatrix(int height, int width) {
        return new Cell4Design[GRID_HEIGHT][GRID_WIDTH];
    }

    @Override
    public Cell4Design createCell() {
        return new Cell4Design();
    }
}
