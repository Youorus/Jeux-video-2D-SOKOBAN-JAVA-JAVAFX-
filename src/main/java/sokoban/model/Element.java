package sokoban.model;

import java.util.HashSet;
import java.util.Set;


public abstract class Element {

    public abstract String getImage();

    public abstract ElementType getType();

    public static Element fromSymbol(char symbol) {
        switch (symbol) {
            case '.':
                return new Goal(); // Par exemple, si '.' représente un objectif
            case '#':
                return new Wall(); // Si '#' représente un mur, par exemple
            case '@':
                return new Player();
            case ' ':
                return new Ground();
            case '$':
                return new Box();
            default:
                return null; // Retourne null si le symbole ne correspond à aucun élément connu
        }
    }

}
