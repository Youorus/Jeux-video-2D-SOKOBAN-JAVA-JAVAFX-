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


    public void movePlayerUp(Element player) {
        // Récupérer la position actuelle du joueur
        int[] playerPosition = getPlayerPosition();

        // Supprimer le joueur de sa position actuelle
        remove(playerPosition[0], playerPosition[1], player);

        // Vérifier si le joueur peut se déplacer vers le haut (il ne peut pas sortir de la grille)
        if (playerPosition[0] > 0) {
            // Ajouter le joueur à la cellule juste au-dessus
            add(playerPosition[0] - 1, playerPosition[1], player);
        }
    }

    public void movePlayerDown(Element player) {
        // Récupérer la position actuelle du joueur
        int[] playerPosition = getPlayerPosition();

        // Supprimer le joueur de sa position actuelle
        remove(playerPosition[0], playerPosition[1], player);

        // Vérifier si le joueur peut se déplacer vers le haut (il ne peut pas sortir de la grille)
        if (playerPosition[0] > 0) {
            // Ajouter le joueur à la cellule juste au-dessus
            add(playerPosition[0] + 1, playerPosition[1], player);
        }
    }


    public void movePlayerRight(Element player) {
        // Récupérer la position actuelle du joueur
        int[] playerPosition = getPlayerPosition();

        // Supprimer le joueur de sa position actuelle
        remove(playerPosition[0], playerPosition[1], player);

        // Vérifier si le joueur peut se déplacer vers le haut (il ne peut pas sortir de la grille)
        if (playerPosition[0] > 0) {
            // Ajouter le joueur à la cellule juste au-dessus
            add(playerPosition[0], playerPosition[1] + 1, player);
        }
    }

    public void movePlayerLeft(Element player, Element wall, Element box) {
        // Récupérer la position actuelle du joueur
        int[] playerPosition = getPlayerPosition();
        int currentRow = playerPosition[0];
        int nextColumn = playerPosition[1] - 1; // Déplacement vers la gauche

        // Supprimer le joueur de sa position actuelle
        remove(currentRow, playerPosition[1], player);

        //verifie si le deplacement du joueur est possible dans les bornes de la grille
        if (nextColumn >= 0 && nextColumn < GRID_WIDTH) {
            if (getCellsElements(currentRow, nextColumn).contains(wall)){
                // Si la cellule suivante contient un mur, garder le joueur à sa position actuelle
                add(currentRow, playerPosition[1], player);
            } else if (getCellsElements(currentRow, nextColumn).contains(box)){
                // Vérifier si la cellule après la boîte contient un mur
                boolean nextCellAfterBoxContainsWall = getCellsElements(currentRow, nextColumn - 1).contains(wall);
                if (nextCellAfterBoxContainsWall) {
                    // Si la cellule après la boîte contient un mur, garder le joueur à sa position actuelle
                    add(currentRow, playerPosition[1], player);
                } else {
                    // Sinon, déplacer le joueur et la boîte vers la cellule suivante
                    remove(currentRow, nextColumn, box);
                    add(currentRow, nextColumn - 1, box);
                    add(currentRow, nextColumn, player);

                    //counter le deplacement du joueur
                    setMoveCount(moveCount.get() + 1);
                }
            } else {
                // Sinon, déplacer le joueur vers la cellule suivante
                add(currentRow, nextColumn, player);
                moveCount.set(moveCount.get() + 1);
            }
        } else {
            // Si le joueur atteint la bordure de la grille, le garder à sa position actuelle
            add(currentRow, playerPosition[1], player);
        }
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
