/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import jeugo.exceptions.PasDePlateaudeCetteTaille;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Sacha
 */
public class EnregistrementTest {
    
    public EnregistrementTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of charger method, of class Enregistrement.
     */
    @Test
    public void testCharger() throws PasDePlateaudeCetteTaille {
        PlateauDeJeu plateau = new PlateauDeJeu(16);
        plateau.insererPiece(new Position(0,0), false);
        plateau.insererPiece(new Position(1,0), true);
        plateau.insererPiece(new Position(2,5), false);
        plateau.insererPiece(new Position(3,0), true);
        plateau.insererPiece(new Position(4,0), false);
        plateau.setHandicap(15);
        Vue vue = new Vue();
        
        System.out.println("charger");
        System.out.println("On sauvegarde ça : ");
        vue.afficherPlateau();
        System.out.println("handicap = " + plateau.getHandicap());
        
        String nomFichier = "test";
        Enregistrement.enregistrer(nomFichier, plateau);
        
        System.out.println("Et on charge ça : ");
        PlateauDeJeu plateau2 = Enregistrement.charger(nomFichier);
        vue.afficherPlateau();
        System.out.println("handicap = " + plateau2.getHandicap());
        
        assertEquals(plateau2, plateau);
    }
    
}
