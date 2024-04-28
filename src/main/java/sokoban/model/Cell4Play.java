package sokoban.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Cell4Play extends Cell {

    private final Grid4Play grid4Play;

    public Cell4Play(Grid4Play grid4Play) {
        this.grid4Play = grid4Play;
    }

    public void movePlayerUp(Element player, Element wall, Element box, Element goal) {
        int[] playerPosition = grid4Play.getPlayerPosition();
        int nextRow = playerPosition[0] - 1;
        int currentColumn = playerPosition[1];

        movePlayer(player, wall, box, goal, nextRow, currentColumn, -1, 0);
    }

    public void movePlayerDown(Element player, Element wall, Element box, Element goal) {
        int[] playerPosition = grid4Play.getPlayerPosition();
        int nextRow = playerPosition[0] + 1;
        int currentColumn = playerPosition[1];

        movePlayer(player, wall, box, goal, nextRow, currentColumn, 1, 0);
    }

    public void movePlayerRight(Element player, Element wall, Element box, Element goal) {
        int[] playerPosition = grid4Play.getPlayerPosition();
        int currentRow = playerPosition[0];
        int nextColumn = playerPosition[1] + 1;

        movePlayer(player, wall, box, goal, currentRow, nextColumn, 0, 1);
    }

    public void movePlayerLeft(Element player, Element wall, Element box, Element goal) {
        int[] playerPosition = grid4Play.getPlayerPosition();
        int currentRow = playerPosition[0];
        int nextColumn = playerPosition[1] - 1;

        movePlayer(player, wall, box, goal, currentRow, nextColumn, 0, -1);
    }

    private void movePlayer(Element player, Element wall, Element box, Element goal, int nextRow, int nextColumn, int rowChange, int colChange) {
        int[] playerPosition = grid4Play.getPlayerPosition();

        if (nextRow >= 0 && nextRow < grid4Play.getGridHeight() && nextColumn >= 0 && nextColumn < grid4Play.getGridWidth()) {
            if (grid4Play.getCellsElements(nextRow, nextColumn).contains(box)) {
                boolean nextCellAfterBoxContainsWall = grid4Play.getCellsElements(nextRow + rowChange, nextColumn + colChange).contains(wall);
                if (nextCellAfterBoxContainsWall) {
                    grid4Play.getCellsElements(playerPosition[0], playerPosition[1]).add(player);
                } else if (grid4Play.getCellsElements(nextRow + rowChange, nextColumn + colChange).contains(box)) {
                    grid4Play.getCellsElements(playerPosition[0], playerPosition[1]).add(player);
                } else {
                    moveBox(box, goal, nextRow, nextColumn, rowChange, colChange);
                    grid4Play.getCellsElements(nextRow, nextColumn).add(player);
                    grid4Play.setMoveCount(grid4Play.getMoveCount() + 1);
                }
            } else {
                grid4Play.getCellsElements(nextRow, nextColumn).add(player);
                grid4Play.setMoveCount(grid4Play.getMoveCount() + 1);
            }
        } else {
            grid4Play.getCellsElements(playerPosition[0], playerPosition[1]).add(player);
        }

        grid4Play.getCellsElements(playerPosition[0], playerPosition[1]).remove(player);

        if (grid4Play.getBoxOnGoalCount() == grid4Play.numberGoal()) {
            grid4Play.setPlayerWin(true);
        }
    }

    private void moveBox(Element box, Element goal, int nextRow, int nextColumn, int rowChange, int colChange) {
        grid4Play.getCellsElements(nextRow, nextColumn).remove(box);
        grid4Play.getCellsElements(nextRow + rowChange, nextColumn + colChange).add(box);

        if (grid4Play.getCellsElements(nextRow, nextColumn).contains(goal)) {
            grid4Play.setBoxOnGoalCount(grid4Play.getBoxOnGoalCount() - 1);
        }
        if (grid4Play.getCellsElements(nextRow + rowChange, nextColumn + colChange).contains(goal)) {
            grid4Play.setBoxOnGoalCount(grid4Play.getBoxOnGoalCount() + 1);
        }
    }
}
