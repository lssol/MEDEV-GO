/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.util.ArrayList;

/** Classe pour décrire un groupe de pièces
 *
 * @author solenemoreau
 */
public class Groupe {
    
    /**
     * liste des pièces dans le groupe
     */
    private ArrayList<Piece> pieces;

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
    
    
    
}
