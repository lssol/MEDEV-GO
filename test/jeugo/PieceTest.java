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
public class PieceTest {
    
    public PieceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

 
    /**
     * Test of getibertes method, of class Piece.
     */
    @Test
    public void testGetibertes() throws PasDePlateaudeCetteTaille {
        System.out.println("getibertes");
        PlateauDeJeu pl = new PlateauDeJeu(9);
        Piece testCoin = new Piece(true,new Position(0,0));
        PlateauDeJeu.pieces[0][0] = testCoin;
        Piece test4Lib = new Piece(true,new Position(3,3));
        PlateauDeJeu.pieces[3][3] = test4Lib;
        Piece testVoisins = new Piece(true,new Position(4,1));
        PlateauDeJeu.pieces[4][1] = testVoisins ;
        PlateauDeJeu.pieces[4][2] = new Piece(true,new Position(4,2));
        PlateauDeJeu.pieces[5][1] = new Piece(true,new Position(5,1));
        
        ArrayList<Position> resultCoin = testCoin.getibertes();
        Position resCoin1 = new Position(0,1);
        Position resCoin2 = new Position (1,0);      
        if (resCoin1.equals(resultCoin.get(0)) && resCoin2.equals(resultCoin.get(1))){
            resultCoin.remove(1);
            resultCoin.remove(0);
            if (!resultCoin.isEmpty()){
                fail("libetés en trop");
            }
        } else {
            fail("libertées manquantes");
        }
        
        ArrayList<Position> result4Lib = test4Lib.getibertes();
        Position res4Lib1 = new Position(3,4);
        Position res4Lib2 = new Position (3,2);
        Position res4Lib3 = new Position(4,3);
        Position res4Lib4 = new Position (2,3);
        if (res4Lib1.equals(result4Lib.get(0)) && res4Lib2.equals(result4Lib.get(1)) && res4Lib3.equals(result4Lib.get(2)) && res4Lib4.equals(result4Lib.get(3))){
            result4Lib.remove(3);
            result4Lib.remove(2);
            result4Lib.remove(1);
            result4Lib.remove(0);
            if (!result4Lib.isEmpty()){
                fail("libetés en trop");
            }
        } else {
            fail("libertées manquantes");
        }
        

        ArrayList<Position> ressultVoisins = testVoisins.getibertes();
        Position resVoisins1 = new Position(4,0);
        Position resVoisins2 = new Position (3,1);
        if (resVoisins1.equals(ressultVoisins.get(0)) && resVoisins2.equals(ressultVoisins.get(1))){
            ressultVoisins.remove(1);
            ressultVoisins.remove(0);
            if (!ressultVoisins.isEmpty()){
                fail("libetés en trop");
            }
        } else {
            fail("libertées manquantes");
        }

    }
    
}
