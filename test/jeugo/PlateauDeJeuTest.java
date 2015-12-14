/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jeugo.PlateauDeJeu.pieces;
import jeugo.exceptions.AhYaDejaQuelquUnIci;
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
    public void testTourDeJeu() throws PasDePlateaudeCetteTaille {
        System.out.println("tourDeJeu");
        plateau = new PlateauDeJeu(16);
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
        //try {
            plateau.insererPiece(pos, couleur);
       /* } catch (Exception ex) {
            fail(ex.toString());
        }*/
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
    public void testGetPiecesAutourDe() throws PasDePlateaudeCetteTaille, AhYaDejaQuelquUnIci {
        System.out.println("getPiecesAutourDe");
        plateau = new PlateauDeJeu(16);
        
        PlateauDeJeu.pieces[3][2] = new Piece(false,new Position(3,2));
        PlateauDeJeu.pieces[2][1] = new Piece(false,new Position(2,1));
        
        List pieces = plateau.getPiecesAutourDe(new Position(3,1));
        
        int nbPieces = pieces.size();
        if(nbPieces != 2){
            fail("Il ya 2 voisins normalement, mais il me dit qu'il y en a : " + pieces.size());
        }
    }

    /**
     * Test of chargerNoms method, of class PlateauDeJeu.
     */
    @Ignore
    @Test
    public void testChargerNoms() {
        System.out.println("chargerNoms");
        PlateauDeJeu instance = null;
        instance.chargerNoms();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


/**
     * Test of verifierPosition method, of class PlateauDeJeu.
     */
    @Test
    public void testVerifierPosition() throws PasDePlateaudeCetteTaille {
        PlateauDeJeu pl = new PlateauDeJeu(9);
        PlateauDeJeu.pieces[1][0] = new Piece(true,new Position(1,0));
        PlateauDeJeu.pieces[0][1] = new Piece(true,new Position(0,1));
        // position occupée
        assertFalse(pl.verifierPosition(new Position(0,1), true));
        //suicide impossible
        assertFalse(pl.verifierPosition(new Position(0,0),false));
        // règle de Ko
        
        // pas de liberté mais le voisin non plus (ex figure6 sujet)
        Groupe g = new Groupe();
        g.add(PlateauDeJeu.pieces[2][0] = new Piece(true,new Position(2,0)));
        g.add(PlateauDeJeu.pieces[2][1] = new Piece(true,new Position(2,1)));
        g.add(PlateauDeJeu.pieces[2][2] = new Piece(true,new Position(2,2)));
        g.add(PlateauDeJeu.pieces[3][2] = new Piece(true,new Position(3,2)));
        g.add(PlateauDeJeu.pieces[4][2] = new Piece(true,new Position(4,2)));
        g.add(PlateauDeJeu.pieces[5][2] = new Piece(true,new Position(5,2)));
        g.add(PlateauDeJeu.pieces[6][2] = new Piece(true,new Position(2,0)));
        g.add(PlateauDeJeu.pieces[6][1] = new Piece(true,new Position(2,0)));
        g.add(PlateauDeJeu.pieces[6][0] = new Piece(true,new Position(2,0)));
        g.mettreAjourLiensPieces();
        
        Groupe h = new Groupe();
        h.add(PlateauDeJeu.pieces[3][0] = new Piece(false,new Position(3,0)));
        h.add(PlateauDeJeu.pieces[3][1] = new Piece(false,new Position(3,1)));
        h.add(PlateauDeJeu.pieces[4][1] = new Piece(false,new Position(4,1)));
        h.add(PlateauDeJeu.pieces[5][1] = new Piece(false,new Position(5,1)));
        h.add(PlateauDeJeu.pieces[5][0] = new Piece(false,new Position(5,0)));
        h.mettreAjourLiensPieces();
        
        assertTrue(pl.verifierPosition(new Position(4,0), true));
        
    }

}
