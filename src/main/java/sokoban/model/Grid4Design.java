    package sokoban.model;

    import javafx.beans.binding.Bindings;
    import javafx.beans.binding.LongBinding;

    import java.util.Arrays;

    public class Grid4Design extends Grid<Cell4Design> {
        private static final int GRID_HEIGHT = 10;
        private static final int GRID_WIDTH = 15;



        private final LongBinding filledCellsCount;


        public Grid4Design() {
            super(GRID_HEIGHT, GRID_WIDTH);
            filledCellsCount = Bindings.createLongBinding(() -> Arrays
                    .stream(getMatrix())
                    .flatMap(Arrays::stream)
                    .filter(cell -> !cell.isEmpty())
                    .count());
        }

        public static int getGridWidth() {
            return GRID_WIDTH;
        }

        public static int getGridHeight() {
            return GRID_HEIGHT;
        }



        public boolean hasPlayer() {
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
                    if (getMatrix()[i][j].getCellsElements().contains(new Player())) {
                        return true; // Un joueur a été trouvé
                    }
                }
            }
            return false; // Aucun joueur n'a été trouvé
        }

        public boolean hasGoal() {
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
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

            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
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
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
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


        public boolean isEmpty(int line, int col) {
            return getMatrix()[line][col].isEmpty();
        }



        @Override
        public Cell4Design[][] createMatrix(int height, int width) {
            return new Cell4Design[GRID_HEIGHT][GRID_WIDTH];
        }

        @Override
        public Cell4Design createCell() {
            return new Cell4Design();
        }
    }
