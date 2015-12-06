/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import static jeugo.PlateauDeJeu.pieces;

/**
 *
 * @author solenemoreau
 */
public class Affichage {
    
    public void afficherPlateau() {
        System.out.println("DÉBUT AFFICHAGE DU PLATEAU DE JEU");
        for(int i=0; i < pieces[0].length; i++) {
            String chaine = "";
            for(int j=0; j < pieces[0].length; j++) {   
		if (pieces[i][j] != null) {
                    if (pieces[i][j].getCouleur()) {
                        chaine = chaine + "B";
                    } else {
                        chaine = chaine + "N";
                    }
                } else {
                    chaine = chaine + "*";
                }
            }
            System.out.println(chaine);
        }
        System.out.println("FIN AFFICHAGE DU PLATEAU DE JEU");
    }
    
}
