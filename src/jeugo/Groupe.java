/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Une liste de pieces dont on peut connaitre le nombre de libertes et d'oeils
 * @author solenemoreau, sacha
 */
public class Groupe extends ArrayList<Piece> implements Serializable{

    public Groupe() {
    super();
    }
    
    /**
     * retourne la liste des liberté d'un groupe, c'est à dire la liste des libertés des pièces du groupe
     * @return liste de position qui sont des libertés pour le groupe
     */
    public ArrayList<Position> getLibertes(PlateauDeJeu plateau){
        ArrayList<Position> res = new ArrayList<>();
            for (Piece p:this){
                for (Position posP : p.getibertes(plateau)){
                    boolean test = false;
                    for (Position posRes : res){
                        if (posP.equals(posRes)){
                            test = true;
                        }
                    }
                    if (!test){
                        res.add(posP);
                    }
                }
            }
        return res;
    }
    
    /**
     * vérifie si un groupe a des libertés ou non
     * @return true si le groupe a au moins une liberté
     */
    public boolean aLiberte(PlateauDeJeu plateau){
        return !(this.getLibertes(plateau).isEmpty());
    }
    
    public int getOeils(){
        return 0;
    }
    /**
     * Supprime un groupe d'une matrice fictive
     * @param mat 
     */
    void supprimer(Piece[][] mat) {
        for(int i=0; i < mat[0].length; i++) {
            for(int j=0; j < mat[0].length; j++) {
                if(mat[i][j] != null){
                    if(mat[i][j].getGroupe() == this){
                        mat[i][j] = null;
                    }
                }
            }
        }
    }
    /**
     * Supprimer un groupe du plateau de jeu
    */
    void supprimer(PlateauDeJeu plateau) {
        supprimer(plateau.pieces);
    }
    
    /**
     * Pour toutes les pieces du groupe, met à jour l'attribut Groupe en le mettant à this
     */
    public void mettreAjourLiensPieces() {
        for(Piece piece : this){
            piece.setGroupe(this);
        }
    }
    @Override
    public String toString(){
        String texte = "";
        for(Piece piece : this){
            texte += piece.toString();
        }
        return texte;
    }
}
