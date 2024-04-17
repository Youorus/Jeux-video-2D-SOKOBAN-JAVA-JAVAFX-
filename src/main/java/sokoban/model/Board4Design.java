package sokoban.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sokoban.viewmodel.ErrorBoxViewModel;

public class Board4Design extends Board {


    private final Grid4Design grid4Design = new Grid4Design(this);

    public Grid4Design getGrid4Design() {
        return grid4Design;
    }

    public SimpleBooleanProperty hasPlayerProperty() {
        return grid4Design.getCell4Design().hasPlayerProperty();
    }

    public void setHasPlayer(boolean hasPlayer) {
        grid4Design.getCell4Design().setHasPlayer(hasPlayer);
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
        return (getGrid4Design().getGridWidth() * getGrid4Design().getGridHeight()) / 2;
    }


    private final BooleanBinding isComplete;

    public Board4Design() {
        isComplete = getGrid4Design().filledCellsCountProperty().isEqualTo(getMaxCellsFilds());

    }


    public LongBinding filledCellsCountProperty() {
        return getGrid4Design().filledCellsCountProperty();
    }


    public Cell4Design[][] getMatrix(){
        return getGrid4Design().getMatrix();
    }

    public void add(int line, int col, Element element) {


        getGrid4Design().getCell4Design().add(line, col, element);

        
        setHasPlayer(!getGrid4Design().hasPlayer());
        errorBoxViewModel.playerErrorProperty().bind(hasPlayerProperty());
        setHasBox(!getGrid4Design().hasBox());
        errorBoxViewModel.boxErrorProperty().bind(hasBox);
        setHasGoal(!getGrid4Design().hasGoal());
        errorBoxViewModel.goalErrorProperty().bind(hasGoal);
        setGoalAndTargetEquals(!getGrid4Design().goalTargetEquals());
        errorBoxViewModel.goalAndTargetErrorProperty().bind(goalAndTargetEquals);
    }

}
