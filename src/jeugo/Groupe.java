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

    public Groupe() {
    super();
    }
    
    /**
     * retourne la liste des liberté d'un groupe, c'est à dire la liste des libertés des pièces du groupe
     * @return liste de position qui sont des libertés pour le groupe
     */
    public ArrayList<Position> getLibertes(){
        ArrayList<Position> res = new ArrayList<>();
            for (Piece p:this){
                for (Position posP : p.getibertes()){
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
    public boolean aLiberte(){
        return !(this.getLibertes().isEmpty());
    }
    
    public int getOeils(){
        return 0;
    }

    /**
     * Supprimer un groupe du plateau de jeu
    */
    void Supprimer() {
        for(int i=0; i < PlateauDeJeu.pieces[0].length; i++) {
            for(int j=0; j < PlateauDeJeu.pieces[0].length; j++) {
                if(PlateauDeJeu.pieces[i][j].getGroupe() == this){
                    PlateauDeJeu.pieces[i][j] = null;
                }
            }
        }
    }
    /**
     * Supprime un groupe d'une matrice fictive
     * @param mat 
     */
    void Supprimer(Piece[][] mat) {
        for(int i=0; i < mat[0].length; i++) {
            for(int j=0; j < mat[0].length; j++) {
                if(mat[i][j].getGroupe() == this){
                    mat[i][j] = null;
                }
            }
        }
    }
    /**
     * Pour toutes les pieces du groupe, met à jour l'attribut Groupe en le mettant à this
     */
    public void mettreAjourLiensPieces() {
        for(Piece piece : this){
            piece.setGroupe(this);
        }
    }
}
