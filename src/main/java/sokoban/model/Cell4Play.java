package sokoban.model;

import javafx.collections.ObservableList;

import java.util.Random;

public class Cell4Play extends Cell {

    private final Grid4Play grid4Play;
    private final Random random = new Random();

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

        if (nextRow < 0 || nextRow >= grid4Play.getGridHeight() || nextColumn < 0 || nextColumn >= grid4Play.getGridWidth()) {
            System.out.println("Move out of bounds. Player cannot move.");
            return;
        }

        ObservableList<Element> nextCellElements = grid4Play.getCellsElements(nextRow, nextColumn);
        if (nextCellElements.contains(wall)) {
            System.out.println("Next cell contains a wall. Player cannot move.");
            return;
        }

        if (nextCellElements.contains(box)) {
            int boxNextRow = nextRow + rowChange;
            int boxNextColumn = nextColumn + colChange;
            if (boxNextRow < 0 || boxNextRow >= grid4Play.getGridHeight() || boxNextColumn < 0 || boxNextColumn >= grid4Play.getGridWidth()) {
                System.out.println("Box move out of bounds. Player cannot move.");
                return;
            }
            ObservableList<Element> boxNextCellElements = grid4Play.getCellsElements(boxNextRow, boxNextColumn);
            if (boxNextCellElements.contains(wall) || boxNextCellElements.contains(box)) {
                System.out.println("Box move blocked. Player cannot move.");
                return;
            }
            moveBox(box, goal, nextRow, nextColumn, rowChange, colChange);
        }

        grid4Play.getCellsElements(playerPosition[0], playerPosition[1]).remove(player);
        nextCellElements.add(player);
        grid4Play.setMoveCount(grid4Play.getMoveCount() + 1);

        if (grid4Play.getBoxOnGoalCount() == grid4Play.numberGoal()) {
            grid4Play.setPlayerWin(true);
        }
    }

    private void moveBox(Element box, Element goal, int nextRow, int nextColumn, int rowChange, int colChange) {
        int boxNextRow = nextRow + rowChange;
        int boxNextColumn = nextColumn + colChange;
        grid4Play.getCellsElements(nextRow, nextColumn).remove(box);
        grid4Play.getCellsElements(boxNextRow, boxNextColumn).add(box);

        if (grid4Play.getCellsElements(nextRow, nextColumn).contains(goal)) {
            grid4Play.setBoxOnGoalCount(grid4Play.getBoxOnGoalCount() - 1);
        }
        if (grid4Play.getCellsElements(boxNextRow, boxNextColumn).contains(goal)) {
            grid4Play.setBoxOnGoalCount(grid4Play.getBoxOnGoalCount() + 1);
        }
    }

    public void addMushroomToRandomEmptyCell() {
        Element mushroom = grid4Play.getBoard4Play().getMushroom();
        int height = grid4Play.getGridHeight();
        int width = grid4Play.getGridWidth();
        while (true) {
            int randomRow = random.nextInt(height);
            int randomCol = random.nextInt(width);

            if (grid4Play.getCellsElements(randomRow, randomCol).isEmpty()) {
                grid4Play.add(randomRow, randomCol, mushroom);
                break;
            }
        }
    }
}
