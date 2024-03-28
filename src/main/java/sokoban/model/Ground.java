package sokoban.model;

import java.util.Objects;

public class Ground extends Element {


    @Override
    public String getImage() {
        return "ground.png";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Dans ce cas, deux objets Wall sont considérés comme égaux
        return true;
    }

    @Override
    public int hashCode() {
        // Utilisation de Objects.hash() pour générer le code de hachage
        return Objects.hash(/* attributs à utiliser pour le code de hachage */);
    }
    @Override
    public String toString() {
        return " ";
    }
}
