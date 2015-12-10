/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

/** Classe pour modéliser une pièce
 * @author solenemoreau
 */
public class Piece {
    
    /**
     * couleur de la pièce (true = blanc, false = noir)
     */
    private boolean couleur;
    
    /**
     * groupe dont fait partie la pièce
     * ce groupe peut être constitué d'une seule pièce
     */
    private Groupe groupe;
    
    public Piece(boolean c){
        this.couleur = c;
        this.groupe = new Groupe();
        groupe.add(this);
    }

    public boolean getCouleur() {
        return couleur;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    
    
    
}
