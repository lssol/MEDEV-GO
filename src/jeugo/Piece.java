/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;

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
    
    /**
     * position de la pièce
     */
    private Position position;
    
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

    public Position getPosition(){
        return position;
    }
    
    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    public void setPosition(Position p){
     this.position = p;   
    }
    
    /**
     * retourne la liste des libertés pour la pièce
     * @return liste de position qui sont des libertés de la pièce
     */
    public ArrayList<Position> getibertes(){
        
        
        return null;
    }
}
