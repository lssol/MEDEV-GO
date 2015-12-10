/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;


/**
 * Une liste de pieces dont on peut connaitre le nombre de libertes et d'oeils
 * @author solenemoreau, sacha
 */
public class Groupe extends ArrayList<Piece> {
    /**
     * retourne la liste des liberté d'un groupe, c'est à dire la liste des libertés des pièces du groupe
     * @return liste de position qui sont des libertés pour le groupe
     */
    public ArrayList<Position> getLibertes(){
        return null;
    }
    
    /**
     * vérifie si un groupe a des liberté ou non
     * @return true si le groupe a au moins une liberté
     */
    public boolean aLiberté(){
        return !(this.getLibertes().isEmpty());
    }
    
    public int getOeils(){
        return 0;
    }
}
