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
     * Renvoie -1 si elles sont non comparables (de tailles différentes ou vides)
     * Renvoie 0 si elles sont comparables mais différentes
     * @param mat1 une matrice de pièces
     * @param mat2 une matrice de pièces
     * @return un entier
     */
    public int compareMat(Piece[][] mat1, Piece[][] mat2) {
        if (mat1.length == 0 || mat2.length == 0 || mat1.length != mat2.length) {
            return -1;
        } else {
            if (mat1[1].length != mat2[1].length) {
                return -1;
            } else {
                int m = 0;
                int n = 0;
                int mm = mat2.length;
                int nn = mat2[1].length;
                boolean egal = true; // passera à false si on tombe sur 2 pièces différentes
                boolean finParcours = false; // passera à true lorsque toute la matrice aura été parcourue
                while (egal && !finParcours) {
                    if (mat1[m][n] == null && mat2[m][n] == null) {
                        // les 2 matrices sont vides pour la case [m][n]
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
                        if (mat1[m][n] == null || mat2[m][n] == null) {
                            // la case [m][n] est vide pour 1 des deux matrices (et une seule)
                            egal = false;
                        } else {
                            // la case [m][n] n'est vide dans aucune des 2 matrices
                            if (mat1[m][n].compareCouleurPosition(mat2[m][n])) {
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
     * @param mat une matrice de pièces (une configurations de pièces)
     * @return un booléen
     */
    public boolean existe(Piece[][] mat) {
        // on calcule le nombre de pièces dans la matrice mat
        int nbPieces = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[1].length; j++) {
                if (mat[i][j] != null) {
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
            // trouve passera à true lorsqu'une configuration similaire sera trouvée
            boolean finParcours = false;
            // finParcours passera à true lorsque toute la liste aura été parcourue
            int indListe = 0;
            while (!trouve && !finParcours && listeMatPieces.get(indListe) != null) {
                if (compareMat(mat,listeMatPieces.get(indListe)) == 1) {
                    // on a trouvé une configuration similaire
                    trouve = true;
                }
                // mise à jour de indListe
                if (indListe < listeMatPieces.size()-1) {
                    indListe++;
                } else {
                    // on a fini de parcourir la liste
                    finParcours = true;
                }
            }
            return trouve;
        } else {
            // il n'existe pas de configuration avec le même nombre de pièces
            return false;
        }
    }
    
    public void sauvegarde(){
        int compteur = 0;
        for (int i =0;i<PlateauDeJeu.getWidth();i++){
            for (int j =0; j <PlateauDeJeu.getWidth();j++){
                if (PlateauDeJeu.getPieces()[i][j] != null){
                    compteur ++;
                }
            }
        }
        
        if (this.containsKey(compteur)){
        this.get(compteur).add(PlateauDeJeu.pieces);
        } else {
            ArrayList<Piece[][]> tmp = new ArrayList<>();
            tmp.add(PlateauDeJeu.pieces);
            this.put(compteur, tmp);
        }
    }
}
