package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import sokoban.viewmodel.ErrorBoxViewModel;

public class Board4Design extends Board {

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer.set(hasPlayer);
    }

    public ErrorBox getErrorBox() {
        return errorBox;
    }

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

    private Ground ground = new Ground();
    private final Player_goal playerGoal = new Player_goal();
    private final Box_goal boxGoal = new Box_goal();
    private final Wall wall = new Wall();
    private final Player player = new Player();
    private final Box box = new Box();
    private final Goal goal = new Goal();

    private final ErrorBoxViewModel errorBoxViewModel = new ErrorBoxViewModel(errorBox);
    static final int MAX_FILLED_CELLS = (Grid4Design.getGridWidth() * Grid4Design.getGridHeight()) / 2;

    public Grid4Design getGrid() {
        return grid4Design;
    }


    private final Grid4Design grid4Design = new Grid4Design();
    private final BooleanBinding isComplete;

    public Board4Design() {
        isComplete = grid4Design.filledCellsCountProperty().isEqualTo(MAX_FILLED_CELLS);

    }

    public static int maxFilledCells() {
        return MAX_FILLED_CELLS;
    }

//    public ReadOnlyObjectProperty<Element> valueProperty(int line, int col) {
//        return grid4Design.valueProperty(line, col);
//    }

    public LongBinding filledCellsCountProperty() {
        return grid4Design.filledCellsCountProperty();
    }

    public boolean isComplete () {
        return isComplete.get();
    }

    public boolean isEmpty(int line, int col) {
        return grid4Design.isEmpty(line, col);
    }

    public Cell4Design[][] getMatrix(){
        return grid4Design.getMatrix();
    }

    public void add(int line, int col, Element element) {


        if (element.equals(ground)) {
            grid4Design.getCellsElements(line, col).clear();
        }else if (grid4Design.getCellsElements(line, col).contains(element) ) {
            grid4Design.remove(line, col, element);
        } else if (grid4Design.getCellsElements(line, col).contains(wall) &&  element.equals(player )) {
            System.out.println("impossible de mettre un joueur sur un mur");
        } else if (grid4Design.getCellsElements(line, col).contains(player) &&  element.equals(wall)) {
            System.out.println("impossible de mettre un joueur sur un mur");
        } else if (element.equals(player)) {
            if (grid4Design.hasPlayer()) {
                // Récupérer les coordonnées actuelles du joueur
                int[] playerPosition = grid4Design.getPlayerPosition();
                // Supprimer le joueur de sa position actuelle
                //grid4Design.remove(playerPosition[0], playerPosition[1]);
                grid4Design.remove(playerPosition[0], playerPosition[1], player);
                // Ajouter le joueur à la nouvelle position
                grid4Design.add(line, col, player);
            } else {
                grid4Design.add(line, col, player);
            }
        }else if (grid4Design.getCellsElements(line, col).contains(player) && element.equals(goal)) {
            grid4Design.add(line, col, playerGoal);
        } else if (grid4Design.getCellsElements(line, col).contains(box) && element.equals(goal)) {
            grid4Design.add(line, col, boxGoal);
        } else if (grid4Design.getCellsElements(line, col).contains(wall) &&  element.equals(player)) {
            System.out.println("Impossible d'ajouter un joueur a un mur");
        } else if (grid4Design.getCellsElements(line, col).contains(player) && element.equals(box)) {
            System.out.println("Impossible de placer une caisse sur un joueur");
        } else if (grid4Design.getCellsElements(line, col).contains(goal) && element.equals(goal)) {
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
