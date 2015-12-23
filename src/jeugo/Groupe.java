/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe pour décrirer un groupe (liste de pieces dont on peut connaitre le
 * nombre de libertes)
 *
 * @author solenemoreau, sacha
 */
public class Groupe extends ArrayList<Piece> implements Serializable {

    public Groupe() {
        super();
    }

    /**
     * Méthode qui retourne la liste des liberté d'un groupe, c'est à dire la
     * liste des libertés des pièces du groupe
     *
     * @param plateau un PlateauDeJeu
     * @return la liste de positions qui sont des libertés pour le groupe
     */
    public ArrayList<Position> getLibertes(PlateauDeJeu plateau) {
        ArrayList<Position> res = new ArrayList<>();
        for (Piece p : this) {
            for (Position posP : p.getibertes(plateau)) {
                boolean test = false;
                for (Position posRes : res) {
                    if (posP.equals(posRes)) {
                        test = true;
                    }
                }
                if (!test) {
                    res.add(posP);
                }
            }
        }
        return res;
    }

    /**
     * Méthode pour tester si un groupe a des libertés
     *
     * @param plateau un PlateauDeJeu
     * @return un booléen (true si le groupe a au moins une liberté)
     */
    public boolean aLiberte(PlateauDeJeu plateau) {
        return !(this.getLibertes(plateau).isEmpty());
    }

    /**
     * Méthode qui supprime le groupe appelant d'une matrice fictive de pièces
     *
     * @param mat
     * @return un entier correspondant au nombre de pièces retirées
     */
    private int supprimer(Piece[][] mat) {
        int res = 0;
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != null) {
                    if (mat[i][j].getGroupe() == this) {
                        mat[i][j] = null;
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Méthode qui supprime le groupe appelant d'un plateau de jeu
     *
     * @param plateau un PlateauDeJeu
     * @return un entier correspondant au nombre de pièces retirées
     */
    public int supprimer(PlateauDeJeu plateau) {
        return supprimer(plateau.pieces);
    }

    /**
     * Méthode qui met à jour l'attribut groupe de toutes les pièces du groupe
     * appelant
     */
    public void mettreAjourLiensPieces() {
        for (Piece piece : this) {
            piece.setGroupe(this);
        }
    }

    @Override
    public String toString() {
        String texte = "";
        for (Piece piece : this) {
            texte += piece.toString();
        }
        return texte;
    }
}
