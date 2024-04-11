package sokoban.viewmodel;
import javafx.beans.binding.LongBinding;
import sokoban.model.Board4Design;
import sokoban.model.Cell4Design;
import sokoban.model.Grid4Design;

public class Board4DesignViewModel extends BoardViewModel{
//    private final IntegerBinding filledCellsCount;
//    private final BooleanExpression isGridHalfFilled;

    private final Grid4DesignViewModel grilleViewModel;

    public ErrorBoxViewModel getErrorBoxViewModel() {
        return errorBoxViewModel;
    }

    public ToolsBoxViewModel getToolsBoxViewModel() {
        return toolsBoxViewModel;
    }

    private final ToolsBoxViewModel toolsBoxViewModel;

private final ErrorBoxViewModel errorBoxViewModel;

    public Board4Design getBoard4Design() {
        return board4Design;
    }

    private final Board4Design board4Design;
    public Board4DesignViewModel(Board4Design board4Design) {
        this.board4Design = board4Design;
        grilleViewModel = new Grid4DesignViewModel(board4Design);
        this.toolsBoxViewModel = new ToolsBoxViewModel(board4Design.getToolsBox());
        this.errorBoxViewModel = new ErrorBoxViewModel(board4Design.getErrorBox());
    }


    public Cell4Design[][] getMatrix(){
       return board4Design.getMatrix();
    }

    public Grid4DesignViewModel getGridViewModel() {
        return grilleViewModel;
    }

    public int maxFilledCells() {
        return board4Design.getMaxCellsFilds();
    }
    public LongBinding filledCellsCountProperty() {
        return board4Design.filledCellsCountProperty();
    }


}
