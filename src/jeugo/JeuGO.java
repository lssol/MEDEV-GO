/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import interfaceGraphique.Goban;
import interfaceGraphique.Lancement;

/**
 * Classe principale
 *
 * @author solenemoreau
 */
public class JeuGO {

    /**
     * Méthode main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Goban goban = new Goban();
        Lancement lancement = new Lancement(goban, true);
        lancement.setVisible(true);

    }

}
