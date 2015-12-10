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
 * Classe pour décrire un plateau de jeu
 * @author Sacha
 */
public final class PlateauDeJeu {
    /*
     * Matrice des pièces sur le plateau de jeu
     */
    public static Piece pieces[][];
    /**
     * Taille du plateau de jeu
     */
    private static int width;
    /**
     * Nom du joueur blanc
     */
    private String jBlanc;
    /**
     * Nom du joueur noir
     */
    private String jNoir;
    /**
     * Pièces capturées par le joueur blanc
     */
    private ArrayList<Piece> prisonBlanc;
    /** 
     * Pièces capturées par le joueur noir
     */
    private ArrayList<Piece> prisonNoir;
    /**
     * Nombre de pierres noires de handicap
     */
    private int handicap;
    /**
     * Liste des tailles de plateau acceptées
     */
    private ArrayList<Groupe> groupes;
    private ArrayList<Groupe> groupesTampon;
    private List taillesOk;
    private Vue vue;
    /**
     * Crée un plateau de la taille indiquée dans width, refuse de le créer si 
     * la taille ne fait pas partie des tailles acceptées
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
        vue = new Vue();
        String[] noms = vue.demanderNomsJoueurs();
        jBlanc = noms[0];
        jNoir = noms[1];
    }
   
    /**
     * Décrit un tour de jeu
     */
    public void tourDeJeu(){
        //Les blancs jouent
        jouer(true);
        supprimerGroupes();

        //Les noirs
        jouer(false);
        supprimerGroupes();
    }

    /**
     * Supprime les groupes qui n'ont plus de libertes... :'(
     * On n'analyse que les groupes qui sont dans le tampon, ce qui permet d'eviter de parcourir tous les groupes
     * (Remarque :pas besoin de transmettre la couleur, grâce au systeme de tampon)
     */
    private void supprimerGroupes() {
        for(Groupe groupe : groupesTampon) {
            if (groupe.getLibertes() == 0) {
                groupes.remove(groupe);
            }
        }
    }

    /**
     * Un joueur joue (interaction avec l'utilisateur via Affichage)
     * @param couleur est la couleur qui joue : true = blanc
     */
    private void jouer(boolean couleur) {

    }
    
    /**
     * Cree une copie de la matrice, insere la piece, Met à jour les groupes, 
     * verifie les libertes et met à jour ou non la vrai matrice, selon que 
     * l'insertion est légale
     * @param pos la position de la pièce
     * @return true si l'insertion a reussi, false sinon
     */
    public void insererPiece(int[] pos, boolean couleur) throws AhYaDejaQuelquUnIci {
        int x  = pos[0];
        int y = pos[1];
        groupesTampon = new ArrayList<Groupe>(); // On reinitialise le tampon facilement, beni soit le garbage collector....

        if(pieces[x][y] != null){
            throw new AhYaDejaQuelquUnIci(pos);
        }

        Piece[][] copie = pieces.clone();
        copie[x][y] = new Piece(couleur);

        Groupe nouveauGroupe = new Groupe();

        // On parcours les pieces autour de notre position, quand on tombe sur une piece de même couleur, on ajoute son groupe au nouveau groupe, puis on supprime son groupe
        for(Piece piece : getPiecesAutourDe(pos)){
            if(piece.getCouleur() == couleur){
                nouveauGroupe.addAll(piece.getGroupe());
                groupes.remove(piece.getGroupe());
            }
            else
                groupesTampon.add(piece.getGroupe()); // Permet une amélioration de performances, c'est intelligent.
        }
        groupes.add(nouveauGroupe);
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

    public static Piece[][] getPieces() {
        return pieces;
    }

    public static int getWidth() {
        return width;
    }
}
