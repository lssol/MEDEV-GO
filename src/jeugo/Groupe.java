/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;


/**
 * Une liste de pieces dont on peut connaitre le nombre de libertes et d'oeils
 * @author solenemoreau, sacha
 */
public class Groupe extends ArrayList<Piece> {
    public int getLibertes(){
        return 0;
    }
    public int getOeils(){
        return 0;
    }
}
