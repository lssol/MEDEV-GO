/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeugo.exceptions.AhYaDejaQuelquUnIci;
import jeugo.exceptions.PasDePlateaudeCetteTaille;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sacha
 */
public class PlateauDeJeuTest {
    PlateauDeJeu plateau;

    @BeforeClass
    public static void setUpClass() {
        try {
            PlateauDeJeu plateau = new PlateauDeJeu(16);
        } catch (PasDePlateaudeCetteTaille e) {
            fail("Il accepte pas cette taille : " + 16);
        }
        try {
            PlateauDeJeu plateauQuiDevraitPasMarcher = new PlateauDeJeu(18);
            fail("On ne devrait pas pouvoir créer de plateau de cette taille !!! : " + 18);
        } catch (PasDePlateaudeCetteTaille e) {
            fail("Il accepte pas cette taille : " + 16);
        }
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
     * Test of tourDeJeu method, of class PlateauDeJeu.
     */
    @Test
    public void testTourDeJeu() {
        System.out.println("tourDeJeu");
        try{
            plateau.tourDeJeu();
        }catch(Exception e){
            fail(e.toString());
        }
    }

    /**
     * Test of insererPiece method, of class PlateauDeJeu.
     */
    @Test
    public void testInsererPiece() {
        System.out.println("insererPiece");
        Position pos = new Position(2,3);
        boolean couleur = false;
        // On insere deux pieces, ca doit pas planter
        try {
            plateau.insererPiece(pos, couleur);
        } catch (Exception ex) {
            fail(ex.toString());
        }
        Position pos2 = new Position(3,3);        
        try {
            plateau.insererPiece(pos, couleur);
        } catch (Exception ex) {
            fail(ex.toString());
        }
        // Maintenant, si on veut inserer une piece alors que yen a deja une, ca doit planter !
        Position posLeRetour = new Position(3,3);        
        try {
            plateau.insererPiece(posLeRetour, couleur);
            fail("Mince là on a inséré une piece au même endroit qu'une autre et ça marche");
        } catch (Exception ex) {

        }
        
    }

    /**
     * Test of getPiecesAutourDe method, of class PlateauDeJeu.
     */
    @Test
    public void testGetPiecesAutourDe() {
        System.out.println("getPiecesAutourDe");
        int[] pos = null;
        PlateauDeJeu instance = null;
        List<Piece> expResult = null;
        List<Piece> result = instance.getPiecesAutourDe(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPieces method, of class PlateauDeJeu.
     */
    @Test
    public void testGetPieces() {
        System.out.println("getPieces");
        Piece[][] expResult = null;
        Piece[][] result = PlateauDeJeu.getPieces();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class PlateauDeJeu.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        int expResult = 0;
        int result = PlateauDeJeu.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
