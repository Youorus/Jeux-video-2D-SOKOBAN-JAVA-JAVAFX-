package sokoban.viewmodel;

import sokoban.model.Board;

public class GridViewModel {

    private final Board board;
    

    GridViewModel(Board board) {
        this.board = board;
    }

    public CellViewModel getCellViewModel(int line, int col) {
        return new CellViewModel(line, col, board);
    }

    public void addElement() {
        // Implémentez la logique pour ajouter un élément à la grille
        // Par exemple, vous pouvez avoir une méthode dans Board pour ajouter un élément à une certaine position
        // board.addElement(line, col, elementType);
    }

    // Supprimer un élément de la grille
    public void removeElement() {
        // Implémentez la logique pour supprimer un élément de la grille
        // Par exemple, vous pouvez avoir une méthode dans Board pour supprimer un élément à une certaine position
        // board.removeElement(line, col);
    }
}