package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;

import static sokoban.model.Grid4Design.getGridHeight;
import static sokoban.model.Grid4Design.getGridWidth;

public class Grid4Play extends Grid<Cell4Play>  {
    private static final int GRID_HEIGHT = 10;
    private static final int GRID_WIDTH = 15;

    public int getMoveCount() {
        return moveCount.get();
    }

    public IntegerProperty moveCountProperty() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount.set(moveCount);
    }

    private final IntegerProperty moveCount = new SimpleIntegerProperty(0);


    public Grid4Play() {
        super(GRID_HEIGHT, GRID_WIDTH);
    }


    @Override
    protected Cell4Play[][] createMatrix(int height, int width) {
        return new Cell4Play[GRID_HEIGHT][GRID_WIDTH];
    }

    @Override
    public Cell4Play createCell() {
        return new Cell4Play();
    }


    //une methode copyElement qui doit me creer une grille CellPlay mais avec les information de cell4Design
    public void copyElements(Grid4Design grid4Design){
        for (int i = 0; i < getGridHeight(); ++i) {
            for (int j = 0; j < getGridWidth(); ++j) {
                // Récupérer la liste des éléments de la cellule de grid4Design
                // et la vider
                this.getCellsElements(i, j).clear();

                // Ajouter tous les éléments de la cellule de grid4Design
                // à la cellule correspondante de grid4Play
                this.getCellsElements(i, j).addAll(grid4Design.getCellsElements(i, j));
            }
        }
    }

    public void add(int line, int col, Element element) {
        getMatrix()[line][col].getCellsElements().add(element);
    }

    public void remove(int line, int col, Element element) {
        getMatrix()[line][col].getCellsElements().remove(element);
    }


    public void movePlayerUp(Element player, Element wall, Element box) {
        int[] playerPosition = getPlayerPosition();
        int nextRow = playerPosition[0] - 1;
        int currentColumn = playerPosition[1];

        movePlayer(player, wall, box, nextRow, currentColumn, -1, 0);
    }

    public void movePlayerDown(Element player, Element wall, Element box) {
        int[] playerPosition = getPlayerPosition();
        int nextRow = playerPosition[0] + 1;
        int currentColumn = playerPosition[1];

        movePlayer(player, wall, box, nextRow, currentColumn, 1, 0);
    }

    public void movePlayerRight(Element player, Element wall, Element box) {
        int[] playerPosition = getPlayerPosition();
        int currentRow = playerPosition[0];
        int nextColumn = playerPosition[1] + 1;

        movePlayer(player, wall, box, currentRow, nextColumn, 0, 1);
    }

    public void movePlayerLeft(Element player, Element wall, Element box) {
        int[] playerPosition = getPlayerPosition();
        int currentRow = playerPosition[0];
        int nextColumn = playerPosition[1] - 1;

        movePlayer(player, wall, box, currentRow, nextColumn, 0, -1);
    }

    private void movePlayer(Element player, Element wall, Element box, int nextRow, int nextColumn, int rowChange, int colChange) {
        int[] playerPosition = getPlayerPosition();

        if (nextRow >= 0 && nextRow < GRID_HEIGHT && nextColumn >= 0 && nextColumn < GRID_WIDTH) {
            if (getCellsElements(nextRow, nextColumn).contains(wall)) {
                // Si la cellule suivante contient un mur, garder le joueur à sa position actuelle
                add(playerPosition[0], playerPosition[1], player);
            } else if (getCellsElements(nextRow, nextColumn).contains(box)) {
                // Vérifier si la cellule après la boîte contient un mur
                boolean nextCellAfterBoxContainsWall = getCellsElements(nextRow + rowChange, nextColumn + colChange).contains(wall);
                if (nextCellAfterBoxContainsWall || nextRow == GRID_HEIGHT - 1 || nextColumn == GRID_WIDTH - 1) {
                    // Si la cellule après la boîte contient un mur ou si la boîte est à la fin de la grille, garder le joueur à sa position actuelle
                    add(playerPosition[0], playerPosition[1], player);
                } else if (getCellsElements(nextRow + rowChange, nextColumn + colChange).contains(box)) {
                    add(playerPosition[0], playerPosition[1], player);
                } else {
                    // Sinon, déplacer le joueur et la boîte vers la cellule suivante
                    remove(nextRow, nextColumn, box);
                    add(nextRow + rowChange, nextColumn + colChange, box);
                    add(nextRow, nextColumn, player);
                    // Compter le déplacement du joueur
                    setMoveCount(moveCount.get() + 1);
                }
            } else {
                // Sinon, déplacer le joueur vers la cellule suivante
                add(nextRow, nextColumn, player);
                moveCount.set(moveCount.get() + 1);
            }
        } else {
            // Si le joueur atteint la bordure de la grille, le garder à sa position actuelle
            add(playerPosition[0], playerPosition[1], player);
        }

        // Supprimer le joueur de sa position actuelle
        remove(playerPosition[0], playerPosition[1], player);
    }





    public int numberGoal() {
        int x = 0;
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Goal())) {
                    x++;
                }
            }
        }
        return x;
    }

}