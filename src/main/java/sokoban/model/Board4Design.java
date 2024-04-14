package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sokoban.viewmodel.ErrorBoxViewModel;

public class Board4Design extends Board {

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer.set(hasPlayer);
    }

    public ErrorBox getErrorBox() {
        return errorBox;
    }


    public BooleanProperty gridEditedProperty() {
        return gridEdited;
    }

    private final BooleanProperty gridEdited = new SimpleBooleanProperty(false);

    public ToolsBox getToolsBox() {
        return toolsBox;
    }

    private final ToolsBox toolsBox = new ToolsBox();
    private final ErrorBox errorBox = new ErrorBox();
    private final SimpleBooleanProperty hasPlayer = new SimpleBooleanProperty();

    public void setGoalAndTargetEquals(boolean goalAndTargetEquals) {
        this.goalAndTargetEquals.set(goalAndTargetEquals);
    }

    private final SimpleBooleanProperty goalAndTargetEquals = new SimpleBooleanProperty();

    public void setHasBox(boolean hasBox) {
        this.hasBox.set(hasBox);
    }

    private final SimpleBooleanProperty hasBox = new SimpleBooleanProperty();

    public void setHasGoal(boolean hasGoal) {
        this.hasGoal.set(hasGoal);
    }

    private final SimpleBooleanProperty hasGoal = new SimpleBooleanProperty();


    private final ErrorBoxViewModel errorBoxViewModel = new ErrorBoxViewModel(errorBox);
    public int getMaxCellsFilds(){
        return (grid4Design.getGridWidth() * grid4Design.getGridHeight()) / 2;
    }

    public Grid4Design getGrid() {
        return grid4Design;
    }


    private final Grid4Design grid4Design = new Grid4Design();
    private final BooleanBinding isComplete;

    public Board4Design() {
        isComplete = grid4Design.filledCellsCountProperty().isEqualTo(getMaxCellsFilds());

    }


    public LongBinding filledCellsCountProperty() {
        return grid4Design.filledCellsCountProperty();
    }


    public Cell4Design[][] getMatrix(){
        return grid4Design.getMatrix();
    }

    public void add(int line, int col, Element element) {


        if (element.equals(getGround())) {
            grid4Design.getCellsElements(line, col).clear();
        }else if (grid4Design.getCellsElements(line, col).contains(element) ) {
            grid4Design.remove(line, col, element);
        } else if (grid4Design.getCellsElements(line, col).contains(getWall()) &&  element.equals(getPlayer())) {
            System.out.println("impossible de mettre un joueur sur un mur");
        } else if (grid4Design.getCellsElements(line, col).contains(getBox()) &&  element.equals(getWall())) {
            grid4Design.getCellsElements(line, col).clear();
            grid4Design.add(line, col, element);
        } else if (grid4Design.getCellsElements(line, col).contains(getPlayer()) &&  element.equals(getWall())) {
            System.out.println("impossible de mettre un joueur sur un mur");
        } else if (element.equals(getPlayer())) {
            if (grid4Design.hasPlayer()) {
                // Récupérer les coordonnées actuelles du joueur
                int[] playerPosition = grid4Design.getPlayerPosition();
                // Supprimer le joueur de sa position actuelle
                //grid4Design.remove(playerPosition[0], playerPosition[1]);
                grid4Design.remove(playerPosition[0], playerPosition[1], getPlayer());
                // Ajouter le joueur à la nouvelle position
                grid4Design.add(line, col, getPlayer());
            } else {
                grid4Design.add(line, col, getPlayer());
            }
        }else if (grid4Design.getCellsElements(line, col).contains(getPlayer()) && element.equals(getGoal())) {
            grid4Design.add(line, col, getPlayer());
        } else if (grid4Design.getCellsElements(line, col).contains(getBox()) && element.equals(getGoal())) {
            grid4Design.add(line, col, getGoal());
        } else if (grid4Design.getCellsElements(line, col).contains(getWall()) &&  element.equals(getPlayer())) {
            System.out.println("Impossible d'ajouter un joueur a un mur");
        } else if (grid4Design.getCellsElements(line, col).contains(getPlayer()) && element.equals(getBox())) {
            System.out.println("Impossible de placer une caisse sur un joueur");
        } else if (grid4Design.getCellsElements(line, col).contains(getGoal()) && element.equals(getGoal())) {
            System.out.println("Impossible de placer une cible sur une autre cible");
        } else {
            grid4Design.add(line, col, element);
        }
        
        setHasPlayer(!grid4Design.hasPlayer());
        errorBoxViewModel.playerErrorProperty().bind(hasPlayer);
        setHasBox(!grid4Design.hasBox());
        errorBoxViewModel.boxErrorProperty().bind(hasBox);
        setHasGoal(!grid4Design.hasGoal());
        errorBoxViewModel.goalErrorProperty().bind(hasGoal);
        setGoalAndTargetEquals(!grid4Design.goalTargetEquals());
        errorBoxViewModel.goalAndTargetErrorProperty().bind(goalAndTargetEquals);
    }

}
