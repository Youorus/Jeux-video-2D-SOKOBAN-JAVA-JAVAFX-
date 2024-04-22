package sokoban.model;

import javafx.collections.ObservableList;

public class Cell4Design extends Cell {

    public Grid4Design getGrid4Design() {
        return grid4Design;
    }

    private final Grid4Design grid4Design;

    public Cell4Design(Grid4Design grid4Design) {
        this.grid4Design = grid4Design;
    }

    public void add(int line, int col, Element element) {
        ObservableList<Element> cellElements = grid4Design.getCellsElements(line, col);

        Element ground = grid4Design.getBoard4Design().getGround();
        Element wall = grid4Design.getBoard4Design().getWall();
        Element player = grid4Design.getBoard4Design().getPlayer();
        Element goal = grid4Design.getBoard4Design().getGoal();
        Element box = grid4Design.getBoard4Design().getBox();


        if (element.equals(ground)) {
            cellElements.clear();
        } else if (cellElements.contains(element)) {
            grid4Design.remove(line, col, element);
        }else if (cellElements.contains(player) && element.equals(goal)) {
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(box) && element.equals(player)) {
            System.out.println("impossible");
        }else if (cellElements.contains(wall) && element.equals(goal) ) {
            cellElements.clear();
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(box) && element.equals(wall)) {
            cellElements.clear();
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(goal) && element.equals(player)) {
            
        } else if (!grid4Design.hasPlayer() && cellElements.contains(wall) && element.equals(player)) {
            cellElements.clear();
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(player) && element.equals(wall)) {
            System.out.println("impossible de mettre un joueur sur un mur");
        } else if (element.equals(player)) {
            if (grid4Design.hasPlayer()) {
                // Récupérer les coordonnées actuelles du joueur
                int[] playerPosition = grid4Design.getPlayerPosition();
                // Supprimer le joueur de sa position actuelle
                //grid4Design.remove(playerPosition[0], playerPosition[1]);
                if (grid4Design.getCellsElements(playerPosition[0], playerPosition[1]).contains(goal)){
                    grid4Design.getCellsElements(playerPosition[0], playerPosition[1]).clear();
                    grid4Design.getCellsElements(playerPosition[0], playerPosition[1]).add(goal);
                }

                if (cellElements.contains(wall)){
                    cellElements.clear();
                }

                grid4Design.remove(playerPosition[0], playerPosition[1], element);
                // Ajouter le joueur à la nouvelle position
                grid4Design.add(line, col, element);
            } else {
                grid4Design.add(line, col, element);
            }
        } else if (cellElements.contains(box) && element.equals(goal)) {
            grid4Design.add(line, col, goal);
        }else if (cellElements.contains(player) && element.equals(box)) {
            System.out.println("Impossible de placer une caisse sur un joueur");
        } else if (cellElements.contains(goal) && element.equals(goal)) {
            System.out.println("Impossible de placer une cible sur une autre cible");
        }else if (cellElements.contains(goal) && element.equals(box)) {
            System.out.println("");
        }else {
            grid4Design.add(line, col, element);
        }
    }
}
