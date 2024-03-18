package sokoban.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Element {

    private Set<Element> elementsCells = new HashSet<>();


    public abstract String getImage();
}
