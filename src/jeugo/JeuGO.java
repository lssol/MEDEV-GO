/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import jeugo.exceptions.PasDePlateaudeCetteTaille;

/**
 *
 * @author solenemoreau
 */
public class JeuGO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PlateauDeJeu plateau = new PlateauDeJeu(16);
            plateau.tourDeJeu();
        } catch (PasDePlateaudeCetteTaille pasDePlateaudeCetteTaille) {
            pasDePlateaudeCetteTaille.printStackTrace();
        }



    }
    
}
