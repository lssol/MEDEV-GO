/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;

/**
 * Classe pour décrire un plateau de jeu
 * @author Sacha
 */
public final class PlateauDeJeu {
    /**
     * Matrice des pièces sur le plateau de jeu
     */
    public static Piece pieces[][];
    /**
     * Taille du plateau de jeu
     */
    private int width;
    /**
     * Nom du joueur blanc
     */
    private String jBlanc;
    /**
     * Nom du joueur noir
     */
    private String jNoir;
    /**
     * Pièces capturées par le joueur blanc
     */
    private ArrayList<Piece> prisonBlanc;
    /** 
     * Pièces capturées par le joueur noir
     */
    private ArrayList<Piece> prisonNoir;
    /**
     * Nombre de pierres noires de handicap
     */
    private int handicap;
    /**
     * Liste des tailles de plateau acceptées
     */
    private ArrayList<Integer> taillesOk;
    
    /**
     * Crée un plateau de la taille indiquée dans width, refuse de le créer si 
     * la taille ne fait pas partie des tailles acceptées
     * @param width 
     */
    public PlateauDeJeu(int width) {
        this.taillesOk = new ArrayList<>();
       
    }
   
    /**
     * Décrit un tour de jeu
     */
    public void tourDeJeu(){
        
    }
    
    /**
     * Cree une copie de la matrice, insere la piece, Met à jour les groupes, 
     * verifie les libertes et met à jour ou non la vrai matrice, selon que 
     * l'insertion est légale
     * @param pos la position de la pièce
     * @return true si l'insertion a reussi, false sinon
     */
    public boolean insererPiece(int[][] pos){
        return false;
    }
    
    
}
