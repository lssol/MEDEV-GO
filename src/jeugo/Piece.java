/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe pour modéliser une pièce
 *
 * @author solenemoreau
 */
public class Piece implements Serializable {

    /**
     * Couleur de la pièce (true = blanc, false = noir)
     */
    private boolean couleur;

    /**
     * Groupe dont fait partie la pièce ce groupe peut être constitué d'une
     * seule pièce
     */
    private Groupe groupe;

    /**
     * Position de la pièce
     */
    private Position position;

    public Piece(boolean c, Position p) {
        this.couleur = c;
        this.groupe = new Groupe();
        groupe.add(this);
        this.position = p;
    }

    public boolean getCouleur() {
        return couleur;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Position getPosition() {
        return position;
    }

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }

    public void setGroupe(Groupe groupe) {
        //this.groupe.remove(this);
        this.groupe = groupe;
        //this.groupe.add(this);
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    /**
     * Méthode qui retourne la liste des libertés pour la pièce
     *
     * @param plateau un PlateauDeJeu
     * @return une liste de positions qui sont des libertés de la pièce
     */
    public ArrayList<Position> getibertes(PlateauDeJeu plateau) {

        ArrayList lib = new ArrayList<>();

        if (position.getY() < plateau.getWidth() - 1) {
            Piece haut = plateau.pieces[position.getX()][position.getY() + 1];
            if (haut == null) {
                lib.add(new Position(position.getX(), position.getY() + 1));
            }
        }

        if (position.getY() > 0) {
            Piece bas = plateau.pieces[position.getX()][position.getY() - 1];
            if (bas == null) {
                lib.add(new Position(position.getX(), position.getY() - 1));
            }
        }

        if (position.getX() < plateau.getWidth() - 1) {
            Piece droite = plateau.pieces[position.getX() + 1][position.getY()];
            if (droite == null) {
                lib.add(new Position(position.getX() + 1, position.getY()));
            }
        }

        if (position.getX() > 0) {
            Piece gauche = plateau.pieces[position.getX() - 1][position.getY()];
            if (gauche == null) {
                lib.add(new Position(position.getX() - 1, position.getY()));
            }
        }

        return lib;
    }

    /**
     * Méthode qui compare la position et la couleur de la pièce actuelle avec
     * une autre pièce
     *
     * @param p une pièce
     * @return un booléen (true si c'est identique, false sinon)
     */
    public boolean compareCouleurPosition(Piece p) {
        return this.couleur == p.getCouleur() && this.position.equals(p.getPosition());
    }

    public String toString() {
        String texteCouleur;
        texteCouleur = couleur ? "Blanc" : "Noir";

        return texteCouleur + " : (" + this.position.getX() + ", " + this.position.getY() + ")";
    }
}
