/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;

/**
 *
 * @author Sacha
 */
public final class PlateauDeJeu {
    Piece pieces[][];
    int width;
    String jBlanc;
    String jNoir;
    int handicap;
    ArrayList<Integer> taillesOk;
    
    /**
     * Crée un plateau de la taille indiquée dans width, refuse de le créer si la taille ne fait pas partie des tailles acceptées
     * @param width 
     */
    public PlateauDeJeu(int width) {
        this.taillesOk = new ArrayList<>();
       
    }
   
    /**
     * Decris un tour de jeu
     */
    public void tourDeJeu(){
        
    }
    /**
     * Cree une copie de la matrice, insere la piece, Met à jour les groupes, 
     * verifie les libertes et met à jour ou non la vrai matrice, selon que l'insertion est légale
     * @param pos la position de la pièce
     * @return true si l'insertion a reussi, false sinon
     */
    public boolean insererPiece(int[][] pos){
        return false;
    }
    
    
}
