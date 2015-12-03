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
    
    private boolean couleur;
    
    private Groupe groupe;

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
