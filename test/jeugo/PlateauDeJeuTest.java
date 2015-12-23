/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.List;
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
     * Test of insererPiece method, of class PlateauDeJeu.
     */
    @Test
    public void testInsererPiece() throws PasDePlateaudeCetteTaille {
        System.out.println("insererPiece");
        plateau = new PlateauDeJeu(16);
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
                
    }

    /**
     * Test of getPiecesAutourDe method, of class PlateauDeJeu.
     */
    @Test
    public void testGetPiecesAutourDe() throws PasDePlateaudeCetteTaille, AhYaDejaQuelquUnIci {
        System.out.println("getPiecesAutourDe");
        plateau = new PlateauDeJeu(16);
        
        plateau.pieces[3][2] = new Piece(false,new Position(3,2));
        plateau.pieces[2][1] = new Piece(false,new Position(2,1));
        
        List pieces = plateau.getPiecesAutourDe(new Position(3,1));
        
        int nbPieces = pieces.size();
        if(nbPieces != 2){
            fail("Il ya 2 voisins normalement, mais il me dit qu'il y en a : " + pieces.size());
        }
    }

    

/**
     * Test of verifierPosition method, of class PlateauDeJeu.
     */
    @Test
    public void testVerifierPosition() throws PasDePlateaudeCetteTaille {
        PlateauDeJeu plateau = new PlateauDeJeu(9);
        plateau.pieces[1][0] = new Piece(true,new Position(1,0));
        plateau.pieces[0][1] = new Piece(true,new Position(0,1));
        // position occupée
        assertFalse(plateau.verifierPosition(new Position(0,1), true));
        //suicide impossible
        assertFalse(plateau.verifierPosition(new Position(0,0),false));
        // règle de Ko
        
        // pas de liberté mais le voisin non plus (ex figure6 sujet)
        Groupe g = new Groupe();
        g.add(plateau.pieces[2][0] = new Piece(true,new Position(2,0)));
        g.add(plateau.pieces[2][1] = new Piece(true,new Position(2,1)));
        g.add(plateau.pieces[2][2] = new Piece(true,new Position(2,2)));
        g.add(plateau.pieces[3][2] = new Piece(true,new Position(3,2)));
        g.add(plateau.pieces[4][2] = new Piece(true,new Position(4,2)));
        g.add(plateau.pieces[5][2] = new Piece(true,new Position(5,2)));
        g.add(plateau.pieces[6][2] = new Piece(true,new Position(2,0)));
        g.add(plateau.pieces[6][1] = new Piece(true,new Position(2,0)));
        g.add(plateau.pieces[6][0] = new Piece(true,new Position(2,0)));
        g.mettreAjourLiensPieces();
        
        Groupe h = new Groupe();
        h.add(plateau.pieces[3][0] = new Piece(false,new Position(3,0)));
        h.add(plateau.pieces[3][1] = new Piece(false,new Position(3,1)));
        h.add(plateau.pieces[4][1] = new Piece(false,new Position(4,1)));
        h.add(plateau.pieces[5][1] = new Piece(false,new Position(5,1)));
        h.add(plateau.pieces[5][0] = new Piece(false,new Position(5,0)));
        h.mettreAjourLiensPieces();
        
        assertTrue(plateau.verifierPosition(new Position(4,0), true));
        
    }

}
