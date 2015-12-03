/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import jeugo.exceptions.AhYaDejaQuelquUnIci;
import jeugo.exceptions.PasDePlateaudeCetteTaille;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sacha
 */
public final class PlateauDeJeu {
    Piece[][] pieces;
    int width;
    String jBlanc;
    String jNoir;
    int handicap;
    List<Integer> taillesOk;
    List<Groupe> groupes;


    /**
     * Crée un plateau de la taille indiquée dans width, refuse de le créer si la taille ne fait pas partie des tailles acceptées
     * @param width 
     */
    public PlateauDeJeu(int width) throws PasDePlateaudeCetteTaille {
        this.taillesOk = Arrays.asList(9, 16, 19);

        if(!taillesOk.contains((Integer) width)){
            throw new PasDePlateaudeCetteTaille(width);
        }
        else {
            this.width = width;
            pieces = new Piece[width][width];

        }
    }
   
    /**
     * Decris un tour de jeu
     */
    public void tourDeJeu(){
        
    }
    /**
     * Cree une copie de la matrice, insere la piece, Met à jour les groupes, 
     * verifie les libertes et met à jour ou non la vrai matrice, selon que l'insertion est légale
     * <b>C'est une des parties centrales du programme</b>
     * @param pos la position de la pièce ([x,y])
     * @return true si l'insertion a reussi, false sinon
     */
    public boolean insererPiece(int[] pos, boolean couleur) throws AhYaDejaQuelquUnIci {
        int x  = pos[0];
        int y = pos[1];

        if(pieces[x][y] != null){
            throw new AhYaDejaQuelquUnIci(pos);
            return false;
        }

        Piece[][] copie = pieces.clone();
        copie[x][y] = new Piece();

        Groupe nouveauGroupe = new Groupe();

        // On parcours les pieces autour de notre position, quand on tombe sur une piece de même couleur, on ajoute son groupe au nouveau groupe, puis on supprime son groupe
        for(Piece piece : getPiecesAutourDe(pos)){
            if(piece.getCouleur() == couleur){
                nouveauGroupe.addAll(piece.getGroupe());
                groupes.removeAll(piece.getGroupe());
            }
        }
        groupes.add(nouveauGroupe);

        return true;
    }

    /**
     * Permet de recupérer la liste des pièces autour d'une position
     * @param pos la position autour de laquelle on veut trouver les pieces
     * @return la liste des pieces trouvées
     */
    public List<Piece> getPiecesAutourDe(int[] pos){
        int x  = pos[0];
        int y = pos[1];

        List<Piece> liste = new ArrayList<>();

        liste.add(pieces[x+1][y]);
        liste.add(pieces[x+1][y+1]);
        liste.add(pieces[x][y+1]);
        liste.add(pieces[x+1][y+1]);

        return liste;
    }
    
    
}
