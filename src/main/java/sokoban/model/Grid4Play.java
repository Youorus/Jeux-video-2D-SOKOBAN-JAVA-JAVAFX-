package sokoban.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Grid4Play extends Grid<Cell4Play>  {
    private final Random random = new Random();
    private int boundRandomCells ;
    public int getMoveCount() {
        return moveCount.get();
    }

    public boolean isShowMushroom() {
        return showMushroom.get();
    }

    public BooleanProperty showMushroomProperty() {
        return showMushroom;
    }

    public void setShowMushroom(boolean showMushroom) {
        this.showMushroom.set(showMushroom);
    }

    private BooleanProperty showMushroom = new SimpleBooleanProperty(false);

    public IntegerProperty moveCountProperty() {
        return moveCount;
    }

    public int getBoxOnGoalCount() {
        return boxOnGoalCount.get();
    }

    public IntegerProperty boxOnGoalCountProperty() {
        return boxOnGoalCount;
    }

    public void setBoxOnGoalCount(int boxOnGoalCount) {
        this.boxOnGoalCount.set(boxOnGoalCount);
    }

    public boolean isPlayerWin() {
        return playerWin.get();
    }

    public SimpleBooleanProperty playerWinProperty() {
        return playerWin;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin.set(playerWin);
    }

    private final SimpleBooleanProperty playerWin = new SimpleBooleanProperty(false);

    private final  IntegerProperty boxOnGoalCount = new SimpleIntegerProperty();


    public void setMoveCount(int moveCount) {
        this.moveCount.set(moveCount);
    }

    private final IntegerProperty moveCount = new SimpleIntegerProperty(0);

    public Board4Play getBoard4Play() {
        return board4Play;
    }

    public Cell4Play getCell4Play() {
        return cell4Play;
    }


    public int getRandomNumber() {
        Random random = new Random();
        return  random.nextInt(getGridHeight() * getGridWidth());
    }

    private Cell4Play cell4Play;


    private final Board4Play board4Play;

    public Grid4Play(Board4Play board4Play, Grid4Design grid4Design) {
        this.board4Play = board4Play;

        reset(grid4Design.getGridHeight(), grid4Design.getGridWidth());
    }


    @Override
    protected Cell4Play[][] createMatrix(int height, int width) {
        return new Cell4Play[getGridHeight()][getGridWidth()];
    }

    @Override
    public Cell4Play createCell() {
        return cell4Play = new  Cell4Play(this);
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


    public void removeMushroom() {
        for (int i = 0; i < getGridHeight(); i++) {
            for (int j = 0; j < getGridWidth(); j++) {
                if (getMatrix()[i][j].getCellsElements().contains(board4Play.getMushroom())) {
                    getMatrix()[i][j].getCellsElements().removeAll(board4Play.getMushroom());
                }
            }
        }
    }

    public void add(int line, int col, Element element) {
        getMatrix()[line][col].getCellsElements().add(element);
    }

    public void remove(int line, int col, Element element) {
        getMatrix()[line][col].getCellsElements().remove(element);
    }


    public void moveBoxesToRandomEmptyCells() {
        List<int[]> emptyCells = getEmptyNonBorderCells();
        List<int[]> boxPositions = new ArrayList<>();

        // Collect all current box positions
        for (int row = 1; row < getGridHeight() - 1; row++) {
            for (int col = 1; col < getGridWidth() - 1; col++) {
                if (getCellsElements(row, col).contains(new Box())) {
                    boxPositions.add(new int[]{row, col});
                }
            }
        }

        // Shuffle the empty cells to randomize
        Collections.shuffle(emptyCells, random);

        // Move each box to a new random empty cell
        for (int[] boxPos : boxPositions) {
            if (!emptyCells.isEmpty()) {
                int[] newPos = emptyCells.remove(0);
                moveBoxToNewPosition(boxPos[0], boxPos[1], newPos[0], newPos[1]);
            }
        }
    }

    private void moveBoxToNewPosition(int oldRow, int oldCol, int newRow, int newCol) {
        Element box = new Box(); // Assume Box is your box element class
        remove(oldRow, oldCol, box);
        add(newRow, newCol, box);
    }

    private boolean isBorderCell(int row, int col) {
        return row == 0 || row == getGridHeight() - 1 || col == 0 || col == getGridWidth() - 1;
    }

    private List<int[]> getEmptyNonBorderCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 1; row < getGridHeight() - 1; row++) {
            for (int col = 1; col < getGridWidth() - 1; col++) {
                if (getCellsElements(row, col).isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        return emptyCells;
    }

    public void updateBoxOnGoalCount() {
        int count = 0;
        for (int i = 0; i < getGridHeight(); i++) {
            for (int j = 0; j < getGridWidth(); j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Box()) &&
                        getMatrix()[i][j].getCellsElements().contains(new Goal())) {
                    count++;
                }
            }
        }
        setBoxOnGoalCount(count);
    }



    public int numberGoal() {
        int x = 0;
        for (int i = 0; i < getGridHeight(); i++) {
            for (int j = 0; j < getGridWidth(); j++) {
                if (getMatrix()[i][j].getCellsElements().contains(new Goal())) {
                    x++;
                }
            }
        }
        return x;
    }

}