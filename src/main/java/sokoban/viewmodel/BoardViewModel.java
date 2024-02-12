package sokoban.viewmodel;

import javafx.beans.binding.LongBinding;
import sokoban.model.Board;

public class BoardViewModel {

    private final GrilleViewModel grilleViewModel;
    private final Board board;
    public BoardViewModel(Board board) {
        this.board = board;
        grilleViewModel = new GrilleViewModel(board);
    }

    public GrilleViewModel getGrilleViewModel() {
        return grilleViewModel;
    }

//    public LongBinding filledCellsCountProperty() {
//        return board.filledCellsCountProperty();
//    }
}
