/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe pour gérer l'enregistrement des parties
 *
 * @author Sacha
 */
public class Enregistrement {

    /**
     * Méthode qui enregistre un plateau de jeu
     *
     * @param nomFichier un String représentant le nom du fichier (sans
     * l'extension .ser)
     * @param plateau le PlateauDeJeu à enregistrer
     */
    public static void enregistrer(String nomFichier, PlateauDeJeu plateau) {

        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(nomFichier + ".ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(plateau);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Méthode qui charge un plateau de jeu
     *
     * @param nomFichier un String représentant le nom du fichier à charger
     * (sans l'extension .ser)
     * @return un PlateauDeJeu
     */
    public static PlateauDeJeu charger(String nomFichier) {
        ObjectInputStream ois = null;
        PlateauDeJeu plateau = null;
        try {
            final FileInputStream fichier = new FileInputStream(nomFichier + ".ser");
            ois = new ObjectInputStream(fichier);
            plateau = (PlateauDeJeu) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return plateau;
        }

    }
}
