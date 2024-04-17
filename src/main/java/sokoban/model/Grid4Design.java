    package sokoban.model;

    import javafx.beans.binding.Bindings;
    import javafx.beans.binding.LongBinding;
    import javafx.collections.ObservableList;

    import java.util.Arrays;

    public class Grid4Design extends Grid<Cell4Design> {
        private static final int Widht = 10;
        private static final int  Height = 15;

        public Cell4Design getCell4Design() {
            return cell4Design;
        }

        private Cell4Design cell4Design;
        private final LongBinding filledCellsCount;

        public Board4Design getBoard4Design() {
            return board4Design;
        }

        private final Board4Design board4Design;


        public Grid4Design(Board4Design board4Design) {
            this.board4Design = board4Design;
            reset(Widht, Height);
            filledCellsCount = Bindings.createLongBinding(() -> Arrays
                    .stream(getMatrix())
                    .flatMap(Arrays::stream)
                    .filter(cell -> !cell.isEmpty())
                    .count());
        }




        public boolean hasPlayer() {
            for (int i = 0; i < getGridHeight(); i++) {
                for (int j = 0; j < getGridWidth(); j++) {
                    if (getMatrix()[i][j].getCellsElements().contains(new Player())) {
                        return true; // Un joueur a été trouvé
                    }
                }
            }
            return false; // Aucun joueur n'a été trouvé
        }

        public boolean hasGoal() {
            for (int i = 0; i < getGridHeight(); i++) {
                for (int j = 0; j < getGridWidth(); j++) {
                    if (getMatrix()[i][j].getCellsElements().contains(new Goal())) {
                        return true; // Un joueur a été trouvé
                    }
                }
            }
            return false; // Aucun joueur n'a été trouvé
        }

        public boolean goalTargetEquals() {
            int goalCount = 0;
            int boxCount = 0;

            for (int i = 0; i < getGridHeight(); i++) {
                for (int j = 0; j < getGridWidth(); j++) {
                    Cell4Design cell4Design = getMatrix()[i][j];
                    goalCount += (int) cell4Design.getCellsElements().stream()
                            .filter(element -> element instanceof Goal)
                            .count();
                    boxCount += (int) cell4Design.getCellsElements().stream()
                            .filter(element -> element instanceof Box)
                            .count();
                }
            }

            return goalCount == boxCount;
        }


        public boolean hasBox() {
            for (int i = 0; i < getGridHeight(); i++) {
                for (int j = 0; j < getGridWidth(); j++) {
                    if (getMatrix()[i][j].getCellsElements().contains(new Box())) {
                        return true; // Une Box  a été trouvé
                    }
                }
            }
            return false; // Aucun Box n'a été trouvé
        }





        public void add(int line, int col, Element element) {
            getMatrix()[line][col].getCellsElements().add(element);
            filledCellsCount.invalidate();
        }



        public void remove(int line, int col, Element element) {
            getMatrix()[line][col].getCellsElements().remove(element);
            filledCellsCount.invalidate();
        }




        public LongBinding filledCellsCountProperty() {
            return filledCellsCount;
        }




        @Override
        public Cell4Design[][] createMatrix(int height, int width) {
            return new Cell4Design[getGridHeight()][getGridWidth()];
        }



        @Override
        public Cell4Design createCell() {
            return cell4Design = new Cell4Design(this);
        }
    }
