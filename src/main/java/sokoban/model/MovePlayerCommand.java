package sokoban.model;

public class MovePlayerCommand implements Command{
    private final Board4Play board4Play;
    private final  Direction direction;
//    private final int playerRowBeforeMove;
//    private final int playerColBeforeMove;
//    private final int boxRowBeforeMove;
//    private final int boxColBeforeMove;

    public MovePlayerCommand(Board4Play board4Play, Direction direction){
        this.board4Play = board4Play;
        this.direction = direction;
    }


    public void execute() {
        switch (direction) {
            case UP:
                board4Play.movePlayerUp();
                break;
            case DOWN:
                board4Play.movePlayerDown();
                break;
            case LEFT:
                board4Play.movePlayerLeft();
                break;
            case RIGHT:
                board4Play.movePlayerRight();
                break;
        }
    }

    @Override
    public void undo() {
        // Pour annuler un déplacement, nous devons effectuer l'opposé du mouvement initial
        switch (direction) {
            case UP:
                board4Play.movePlayerDown();
                break;
            case DOWN:
                board4Play.movePlayerUp();
                break;
            case LEFT:
                board4Play.movePlayerRight();
                break;
            case RIGHT:
                board4Play.movePlayerLeft();
                break;
        }
    }

    @Override
    public void redo() {
        // Pour refaire un mouvement annulé, nous réexécutons simplement la commande d'origine
        execute();
    }
}
