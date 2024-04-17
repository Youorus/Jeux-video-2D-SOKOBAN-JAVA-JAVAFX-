package sokoban.model;

import javafx.collections.ObservableList;

public class Cell4Design extends Cell {

    private final Grid4Design grid4Design;

    public Cell4Design(Grid4Design grid4Design) {
        this.grid4Design = grid4Design;
    }

    public void add(int line, int col, Element element) {
        ObservableList<Element> cellElements = grid4Design.getCellsElements(line, col);

        // Si l'élément est le sol, efface tous les éléments de la cellule
        if (element.equals(grid4Design.getBoard4Design().getGround())) {
            cellElements.clear();
        }
        // Si la cellule contient déjà l'élément, le supprime
        else if (cellElements.contains(element)) {
            cellElements.remove(element);
        }
        // Si la cellule contient un mur et on essaie de mettre un joueur dessus, affiche un message d'erreur
        else if (cellElements.contains(grid4Design.getBoard4Design().getWall()) && element.equals(grid4Design.getBoard4Design().getPlayer())) {
            System.out.println("Impossible de mettre un joueur sur un mur");
        }
        // Si la cellule contient déjà un joueur et on essaie de mettre une cible, ajoute la cible
        else if (cellElements.contains(grid4Design.getBoard4Design().getPlayer()) && element.equals(grid4Design.getBoard4Design().getGoal())) {
            cellElements.add(element);
        }
        // Si la cellule contient une boîte et on essaie de mettre un mur, efface tous les éléments et ajoute le mur
        else if (cellElements.contains(grid4Design.getBoard4Design().getBox()) && element.equals(grid4Design.getBoard4Design().getWall())) {
            cellElements.clear();
            cellElements.add(element);
        }
        // Si la cellule contient déjà un joueur et on essaie de mettre un mur, affiche un message d'erreur
        else if (cellElements.contains(grid4Design.getBoard4Design().getPlayer()) && element.equals(grid4Design.getBoard4Design().getWall())) {
            System.out.println("Impossible de mettre un joueur sur un mur");
        }
        // Si l'élément est un joueur et un joueur est déjà présent sur la grille, supprime le joueur existant
        // et ajoute le nouveau joueur à la cellule
        else if (element.equals(grid4Design.getBoard4Design().getPlayer()) && grid4Design.hasPlayer()) {
            int[] playerPosition = grid4Design.getPlayerPosition();
            grid4Design.remove(playerPosition[0], playerPosition[1], grid4Design.getBoard4Design().getPlayer());
            cellElements.add(element);
        }
        // Si la cellule contient déjà un joueur et on essaie de mettre une cible, ajoute le joueur à la cellule
        else if (cellElements.contains(grid4Design.getBoard4Design().getPlayer()) && element.equals(grid4Design.getBoard4Design().getGoal())) {
            cellElements.add(grid4Design.getBoard4Design().getPlayer());
        }
        // Si la cellule contient déjà une boîte et on essaie de mettre une cible, ajoute la cible à la cellule
        else if (cellElements.contains(grid4Design.getBoard4Design().getBox()) && element.equals(grid4Design.getBoard4Design().getGoal())) {
            cellElements.add(grid4Design.getBoard4Design().getGoal());
        }
        // Si la cellule contient un mur et on essaie de mettre un joueur, affiche un message d'erreur
        else if (cellElements.contains(grid4Design.getBoard4Design().getWall()) && element.equals(grid4Design.getBoard4Design().getPlayer())) {
            System.out.println("Impossible d'ajouter un joueur à un mur");
        }
        // Si la cellule contient déjà un joueur et on essaie de mettre une boîte, affiche un message d'erreur
        else if (cellElements.contains(grid4Design.getBoard4Design().getPlayer()) && element.equals(grid4Design.getBoard4Design().getBox())) {
            System.out.println("Impossible de placer une caisse sur un joueur");
        }
        // Si la cellule contient déjà une cible et on essaie de mettre une autre cible, affiche un message d'erreur
        else if (cellElements.contains(grid4Design.getBoard4Design().getGoal()) && element.equals(grid4Design.getBoard4Design().getGoal())) {
            System.out.println("Impossible de placer une cible sur une autre cible");
        }
        // Sinon, ajoute simplement l'élément à la cellule
        else {
            cellElements.add(element);
        }
    }
}
