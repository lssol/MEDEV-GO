/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oriane école
 */
public class PositionTest {
    
    public PositionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

  
    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Position instance = new Position();
        assertTrue(instance.equals(new Position(0,0)));
        assertFalse(instance.equals(new Position(2,6)));
    }
    
}
