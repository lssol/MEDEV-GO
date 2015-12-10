/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author solenemoreau
 */
public class Historique extends HashMap<Integer,ArrayList<Piece[][]>>{
    
    /**
     * Teste si une configuration des pièces a déjà existé plus tôt dans le jeu
     * @param p une matrice de pièces (une configurations de pièces)
     * @return un booléen
     */
    public boolean existe(Piece[][] p) {
        // on calcule le nombre de pièces dans la matrice p
        int nbPieces = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[1].length; i++) {
                if (p[i][j] != null) {
                    nbPieces++;
                }
            }    
        }
        if (!this.containsKey(nbPieces)) {
            ArrayList<Piece[][]> listeMatPieces = this.get(nbPieces);
            boolean estEgal = true;
            int indListe = 0;
            while (estEgal && listeMatPieces.get(indListe) != null) {
                indListe++;
            }            
        }
        
        
        
        return true;
    }
    
}
