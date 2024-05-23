package sokoban.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import sokoban.viewmodel.ErrorBoxViewModel;

public class Cell4Design extends Cell {

    public Grid4Design getGrid4Design() {
        return grid4Design;
    }

    public ErrorBoxViewModel getErrorBoxViewModel() {
        return grid4Design.getErrorBoxViewModel();
    }

    private final SimpleBooleanProperty goalAndTargetEquals = new SimpleBooleanProperty();

    public void setHasBox(boolean hasBox) {
        this.hasBox.set(hasBox);
    }

    private final SimpleBooleanProperty hasBox = new SimpleBooleanProperty();

    public void setHasGoal(boolean hasGoal) {
        this.hasGoal.set(hasGoal);
    }

    public void setGoalAndTargetEquals(boolean goalAndTargetEquals) {
        this.goalAndTargetEquals.set(goalAndTargetEquals);
    }


    private final SimpleBooleanProperty hasGoal = new SimpleBooleanProperty();

    private final Grid4Design grid4Design;

    public Cell4Design(Grid4Design grid4Design) {
        this.grid4Design = grid4Design;
    }


    public void add(int line, int col, Element element) {
        ObservableList<Element> cellElements = grid4Design.getCellsElements(line, col);

        Element ground = new Ground();
        Element wall = new Wall();
        Element player = new Player();
        Element goal = new Goal();
        Element box = new Box();


        if (element.equals(ground)) {
            grid4Design.clear(line, col);
        } else if (cellElements.contains(element)) {
            grid4Design.remove(line, col, element);
        }else if (cellElements.contains(player) && element.equals(goal)) {
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(box) && element.equals(player)) {
            System.out.println("impossible");
        }else if (cellElements.contains(wall) && element.equals(goal) ) {
            grid4Design.clear(line, col);
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(box) && element.equals(wall)) {
            grid4Design.clear(line, col);
            grid4Design.add(line, col, element);
        } else if (cellElements.contains(goal) && element.equals(player)) {
            
        } else if (!grid4Design.hasPlayer() && cellElements.contains(wall) && element.equals(player)) {
            grid4Design.clear(line, col);
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
                    grid4Design.clear(line, col);
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



        setHasPlayer(!getGrid4Design().hasPlayer());
        getErrorBoxViewModel().playerErrorProperty().bind(hasPlayerProperty());
        setHasBox(!getGrid4Design().hasBox());
        getErrorBoxViewModel().boxErrorProperty().bind(hasBox);
        setHasGoal(!getGrid4Design().hasGoal());
        getErrorBoxViewModel().goalErrorProperty().bind(hasGoal);
        setGoalAndTargetEquals(!getGrid4Design().goalTargetEquals());
        getErrorBoxViewModel().goalAndTargetErrorProperty().bind(goalAndTargetEquals);
    }
}
