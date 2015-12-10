/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe qui hérite de la classe Java HashMap<Integer,ArrayList<Piece[][]>>
 * Permet de stocker l'historique des configurations de jeu
 * la clé est le nombre de pièces dans la configuration
 * la valeur est une liste de matrices de pièces ayant le même nombre total de pièces
 * @author solenemoreau
 */
public class Historique extends HashMap<Integer,ArrayList<Piece[][]>>{
    
    public Historique() {
        super();
    }
    
    /**
     * Compare deux matrices de pièces
     * Renvoie 1 si elles sont identiques
     * Renvoie -1 si elles sont non comparables (de tailles différentes)
     * Renvoie 0 si elles sont comparables mais différentes
     * @param p1 une matrice de pièces
     * @param p2 une matrice de pièces
     * @return un entier
     */
    public int compareMat(Piece[][] p1, Piece[][] p2) {
        if (p1.length != p2.length) {
            return -1;
        } else {
            if (p1[1].length != p2[1].length) {
                return -1;
            } else {
                int m = 0;
                int n = 0;
                int mm = p2.length;
                int nn = p2[1].length;
                boolean egal = true; // passera à false si on tombe sur 2 pièces différentes
                boolean finParcours = false; // passera à true lorsque toute la matrice aura été parcourue
                while (egal && !finParcours) {
                    if (p1[m][n].equals(p2[m][n])) {
                        // mise à jour des indices de parcours m et n
                        if (n<nn-1) { // est-on sur la dernière colonne ?
                            n++;
                        } else {
                            if (m<mm-1) { // est-on sur la dernière ligne ?
                                n=0;
                                m++;
                            } else {
                                finParcours = true;
                            }
                        }
                    } else {
                        egal = false;
                    }
                }
                if (!egal) {
                    // on est sorti du while car egal est devenu false
                    return 0;
                } else {
                    // on est sorti du while car finParcours est devenu true
                    return 1;
                }
            }
        }
    }
    
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
        // on regarde s'il y a déjà eu une configuration similaire
        // en se limiant aux configurations ayant le même nombre de pièces
        if (this.containsKey(nbPieces)) {
            // il existe une configuration avec le même nombre de pièces
            ArrayList<Piece[][]> listeMatPieces = this.get(nbPieces);
            boolean trouve = false; 
            // trouve passera à vrai lorsqu'une configuration similaire sera trouvée
            int indListe = 0;
            while (!trouve && listeMatPieces.get(indListe) != null) {
                indListe++;
                if (compareMat(p,listeMatPieces.get(indListe)) == 1) {
                    // on a trouvé une configuration similaire
                    trouve = true;
                }
            }
            return trouve;
        } else {
            // il n'existe pas de configuration avec le même nombre de pièces
            return false;
        }
    }
    
}
