/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.Scanner;
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
    
    /**
     * Demande une position au joueur courant
     * ne gère pas le fait que la position demandée est possible ou pas
     * gère uniquement le fait que la position doit être formée de 2 entiers
     * @return un tableau de 2 entiers : [ligne,colonne]
     */
    public int[] demanderPosition() {
        int[] pos = new int[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Où souhaitez-vous poser votre pièce ?");
        System.out.println("Sur quelle ligne ?");
        String ligne = sc.nextLine();
        try {
            pos[1] = Integer.parseInt(ligne);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        System.out.println("Sur quelle colonne ?");
        String colonne = sc.nextLine();
        try {
            pos[2] = Integer.parseInt(colonne);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return pos;
    }
    
}
