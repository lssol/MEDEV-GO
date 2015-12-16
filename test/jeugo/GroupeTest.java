/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;
import jeugo.exceptions.PasDePlateaudeCetteTaille;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oriane école
 */
public class GroupeTest {
    
    public GroupeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getLibertes method, of class Groupe.
     * @throws jeugo.exceptions.PasDePlateaudeCetteTaille
     */
    @Test
    public void testGetLibertes() throws PasDePlateaudeCetteTaille {
        System.out.println("getLibertes");

        PlateauDeJeu pl = new PlateauDeJeu(9);
        Piece test4Lib = new Piece(true,new Position(1,1));
        PlateauDeJeu.pieces[1][1] = test4Lib;

        ArrayList<Position> result4Lib = test4Lib.getGroupe().getLibertes();
        assertTrue(result4Lib.size()==4);
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        for (Position p : result4Lib) {
            a = a|p.equals(new Position(0,1));
            b = b|p.equals(new Position(0,1));
            c = c|p.equals(new Position(1,2));
            d = d|p.equals(new Position(2,1));
        }
        assertTrue(a && b && c && d == true);
        
        Groupe g = new Groupe();
        Piece p1 = new Piece(true, new Position(1,0));
        g.add(p1);
        Piece p2 = new Piece(true, new Position(0,1));
        g.add(p2);
        Piece p3 = new Piece(true, new Position(1,2));
        g.add(p3);
        Piece p4 = new Piece(true, new Position(2,1));
        g.add(p4);
        g.mettreAjourLiensPieces();
        PlateauDeJeu.pieces[1][0]= p1;
        PlateauDeJeu.pieces[0][1] = p2;
        PlateauDeJeu.pieces[1][2] = p3;
        PlateauDeJeu.pieces[2][1] = p4;
       
        ArrayList<Position> resultG = g.getLibertes();
        assertTrue(resultG.size() == 6);
        boolean e = false;
        boolean f = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        for (Position p : resultG) {
            e = e|p.equals(new Position(0,0));
            f = f|p.equals(new Position(2,0));
            h = h|p.equals(new Position(3,1));
            i = i|p.equals(new Position(2,2));
            j = j|p.equals(new Position(1,3));
            k = k|p.equals(new Position(0,2));
        }
        assertTrue(e && f && h && i && j && k == true);
        assertTrue(test4Lib.getGroupe().getLibertes().isEmpty());
    }

    /**
     * Test of aLiberté method, of class Groupe.
     */
    @Test
    public void testALiberte() throws PasDePlateaudeCetteTaille {
        System.out.println("aLiberte");
        
        PlateauDeJeu pl = new PlateauDeJeu(9);
        Piece test4Lib = new Piece(true,new Position(1,1));
        PlateauDeJeu.pieces[1][1] = test4Lib;

        assertTrue(test4Lib.getGroupe().aLiberte());
        
        Groupe g = new Groupe();
        Piece p1 = new Piece(true, new Position(1,0));
        g.add(p1);
        Piece p2 = new Piece(true, new Position(0,1));
        g.add(p2);
        Piece p3 = new Piece(true, new Position(1,2));
        g.add(p3);
        Piece p4 = new Piece(true, new Position(2,1));
        g.add(p4);
        g.mettreAjourLiensPieces();
        PlateauDeJeu.pieces[1][0]= p1;
        PlateauDeJeu.pieces[0][1] = p2;
        PlateauDeJeu.pieces[1][2] = p3;
        PlateauDeJeu.pieces[2][1] = p4;
       
        assertTrue(g.aLiberte());
        assertFalse(test4Lib.getGroupe().aLiberte());
        
    }

    /**
     * Test of getOeils method, of class Groupe.
     */
    @Test
    public void testGetOeils() {
        System.out.println("getOeils");

    }

    /**
     * Test of Supprimer method, of class Groupe.
     */
    @Test
    public void testSupprimer_0args() throws PasDePlateaudeCetteTaille {
        System.out.println("Supprimer");
        PlateauDeJeu plateau = new PlateauDeJeu(16);
        
        //Dans le même groupe (adjacents)
        plateau.insererPiece(new Position(1,1), true);
        plateau.insererPiece(new Position(1,0), true);
        plateau.insererPiece(new Position(1,2), true);
        plateau.insererPiece(new Position(0,2), true);
        //Est adjacent, mais de couleure opposée
        plateau.insererPiece(new Position(1,4), false);
        
        // Sur la diagonale (pas dans le même groupe donc)
        plateau.insererPiece(new Position(2,3), true);
        
        Vue vue = new Vue();
        vue.afficherPlateau();
        PlateauDeJeu.pieces[1][0].getGroupe().Supprimer();

        if(    PlateauDeJeu.pieces[1][1] != null 
            || PlateauDeJeu.pieces[1][0] != null 
            || PlateauDeJeu.pieces[1][2] != null 
            || PlateauDeJeu.pieces[0][2] != null )
        {
            vue.afficherPlateau();
            fail("Il aurait du supprimer tout le groupe...");
        }
        if(PlateauDeJeu.pieces[2][3] == null || PlateauDeJeu.pieces[1][4] == null){
            vue.afficherPlateau();
            fail("Celles là, il aurait pas du les supprimer");
        }
            
    }

    /**
     * Test of Supprimer method, of class Groupe.
     */
    @Test
    public void testSupprimer_PieceArrArr() {
        System.out.println("Supprimer");

    }

    /**
     * Test of mettreAjourLiensPieces method, of class Groupe.
     */
    @Test
    public void testMettreAjourLiensPieces() {
        System.out.println("mettreAjourLiensPieces");

    }
    
}
