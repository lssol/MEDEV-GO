/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author solenemoreau
 */
public class HistoriqueTest {
    
    public HistoriqueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of compareMat method, of class Historique.
     */
    @Test
    public void testCompareMat() {
        Historique h = new Historique();
        
        // 2 matrices de pièces vides
        Piece[][] mat1 = new Piece[0][0];
        Piece[][] mat2 = new Piece[0][0];
        assertEquals(-1,h.compareMat(mat1,mat2));
   
        // 2 matrices de pièces de tailles différentes
        mat1 = new Piece[16][16];
        mat2 = new Piece[9][9];
        assertEquals(-1,h.compareMat(mat1,mat2));
        
        // 2 matrices de pièces de même taille et identiques
        mat1 = new Piece[16][16];
        mat2 = new Piece[16][16];
        Position pos1 = new Position(3,4);
        Piece p1 = new Piece(true,pos1);
        mat1[3][4] = p1;
        Piece p2 = new Piece(true,pos1);
        mat2[3][4] = p2;
        assertEquals(1,h.compareMat(mat1,mat2));
        
        // 2 matrices de pièces de même taille mais différentes (2 pièces, pas au même endroit)
        mat1 = new Piece[16][16];
        mat2 = new Piece[16][16];
        pos1 = new Position(1,1);
        p1 = new Piece(true,pos1);
        mat1[1][1] = p1;
        Position pos2 = new Position(6,3);
        p2 = new Piece(true,pos2);
        mat2[6][3] = p2;
        assertEquals(0,h.compareMat(mat1,mat2));
        
        // 2 matrices de pièces de même taille mais différentes (2 pièces au même endroit de couleur différente)
        mat1 = new Piece[16][16];
        mat2 = new Piece[16][16];
        pos1 = new Position(10,4);
        p1 = new Piece(true,pos1);
        mat1[10][4] = p1;
        p2 = new Piece(false,pos1);
        mat2[10][4] = p2;
        assertEquals(0,h.compareMat(mat1,mat2));
    }
    
    /**
     * Test of existe method, of class Historique.
     */
    @Test
    public void testExiste() {
        
        // configuration 1 : true en (3,4)
        Piece[][] mat1 = new Piece[16][16];
        Position pos1 = new Position(3,4);
        Piece p1 = new Piece(true,pos1);
        mat1[3][4] = p1;
        
        // configuration 2 : false en (3,4)
        Piece[][] mat2 = new Piece[16][16];
        Position pos2 = new Position(3,4);
        Piece p2 = new Piece(true,pos2);
        mat2[3][4] = p2;
        
        // configuration 3 : true en (3,4) et false en (9,7)
        Piece[][] mat3 = new Piece[16][16];
        Position pos3a = new Position(3,4);
        Position pos3b = new Position(9,7);
        Piece p3a = new Piece(true,pos3a);
        Piece p3b = new Piece(false,pos3b);
        mat3[3][4] = p3a;
        mat3[9][7] = p3b;
        
        // configuration 4 : true en (3,4) et true en (9,7)
        Piece[][] mat4 = new Piece[16][16];
        Position pos4a = new Position(3,4);
        Position pos4b = new Position(9,7);
        Piece p4a = new Piece(true,pos4a);
        Piece p4b = new Piece(true,pos4b);
        mat4[3][4] = p4a;
        mat4[9][7] = p4b;

        // historique avec les configurations 1, 2, 3 et 4
        Historique h1234 = new Historique();
        ArrayList<Piece[][]> l1 = new ArrayList<>();
        l1.add(mat1);
        l1.add(mat2);
        ArrayList<Piece[][]> l2 = new ArrayList<>();
        l2.add(mat3);
        l2.add(mat4);
        h1234.put(1,l1);
        h1234.put(2,l2);
        
        // historique avec les configurations 1, 2 et 3
        Historique h123 = new Historique();
        ArrayList<Piece[][]> l3 = new ArrayList<>();
        l3.add(mat1);
        l3.add(mat2);
        ArrayList<Piece[][]> l4 = new ArrayList<>();
        l4.add(mat3);
        h123.put(1,l3);
        h123.put(2,l4);
        
        // tests
        assertTrue(h1234.existe(mat1));
        assertTrue(h1234.existe(mat2));
        assertTrue(h1234.existe(mat3));
        assertTrue(h1234.existe(mat4));
        assertTrue(h123.existe(mat1));
        assertTrue(h123.existe(mat2));
        assertTrue(h123.existe(mat3));
        assertFalse(h123.existe(mat4));
    }

//    /**
//     * Test of sauvegarde method, of class Historique.
//     */
//    @Test
//    public void testSauvegarde() throws PasDePlateaudeCetteTaille {
//        PlateauDeJeu plateau = new PlateauDeJeu(9);
//        plateau.pieces[0][0]=new Piece(true,new Position(0,0));
//        plateau.historique.sauvegarde(plateau);
//        
//        assertTrue(plateau.historique.existe(plateau.pieces));
//    }

    
    
}
