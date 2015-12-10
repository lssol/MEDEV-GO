/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

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
    //private ArrayList<Groupe> groupes;
    private ArrayList<Groupe> groupesTampon;
    private List taillesOk;
    private Vue vue;
    public Historique historique;
    /**
     * Crée un plateau de la taille indiquée dans width, refuse de le créer si 
     * la taille ne fait pas partie des tailles acceptées
     * @param width 
     * @throws jeugo.exceptions.PasDePlateaudeCetteTaille 
     */
    public PlateauDeJeu(int width) throws PasDePlateaudeCetteTaille {
        // initialisation des attributs
        this.historique = new Historique();

        this.taillesOk = Arrays.asList(9, 16, 19);

        if(!taillesOk.contains((Integer) width)){
            throw new PasDePlateaudeCetteTaille(width);
        }
        else {
            PlateauDeJeu.width = width;
            pieces = new Piece[width][width];
        }
    }
    public void chargerNoms(){
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

        //Les noirs
        jouer(false);
    }

    /**
     * Supprime les groupes qui n'ont plus de libertes... :'(
     * On n'analyse que les groupes qui sont dans le tampon, ce qui permet d'eviter de parcourir tous les groupes
     * (Remarque :pas besoin de transmettre la couleur, grâce au systeme de tampon)
     */
    private void supprimerGroupes() {
        for(Groupe groupe : groupesTampon) {
            if (!groupe.aLiberte()) {
                groupe.Supprimer();
            }
        }
    }

    /**
     * Un joueur joue (interaction avec l'utilisateur via Affichage)
     * @param couleur est la couleur qui joue : true = blanc
     */
    private void jouer(boolean couleur){
        Vue vue = new Vue();
        vue.afficherPlateau();
        
        // demander la position ou placer la pièce
        // vérifier la position : tant que la position incorrecte - redemander 
        Position pos = new Position();
        boolean posOK = false;
        while (posOK){
            pos = vue.demanderPosition();
            // posOK = this.verifierPosition(pos);
        }
        
        this.insererPiece(pos, couleur);
        
        // supprimer les groupes sans liberté
        supprimerGroupes();
        
        // enregister l'état du plateau dans historique
        // enregister le mouvement dans le fichier texte
        historique.sauvegarde();

    }
    
    /**
     * vérifie la conformité d'une position choisie avec les règles du GO
     * - regle de Ko (historique)
     * - a des libertés
     * - si pas de liberté : vérif que groupe tampon est supprimé : si oui -> true 
     * @param p position a tester
     * @param couleur joueur en train de poser une pièce
     * @return true si la position est compatible avec les règles
     */
    public boolean verifierPosition(Position p, boolean couleur){
 
/* Pour après, pour pouvoir empecher de mettre à certaines places
        Piece[][] copie = pieces.clone();
        copie[x][y] = new Piece(couleur);
*/
        
        return true;
    }
    
    /**
     * Positionne une pièce de couleur donnée à une position donnée
     * la position est vérifié au préalable
     * les groupes sont mis à jour en fonction de la nouvelle pièce
     * 
     * @param pos la position de la pièce est placée
     * @param couleur la couleur de la pièce positionnée
     */
    public void insererPiece(Position pos, boolean couleur)  {
        groupesTampon = new ArrayList<>(); // On reinitialise le tampon facilement, beni soit le garbage collector....

        this.pieces[pos.getX()][pos.getY()] = new Piece(couleur,pos);
        Groupe nouveauGroupe = new Groupe();

        // On parcours les pieces autour de notre position, quand on tombe sur une piece de 
        // même couleur, on ajoute son groupe au nouveau groupe, puis on supprime son groupe
        for(Piece piece : getPiecesAutourDe(pos)){
            if(piece.getCouleur() == couleur){
                nouveauGroupe.addAll(piece.getGroupe());
            }
            else
                groupesTampon.add(piece.getGroupe()); // Permet une amélioration de performances, c'est intelligent.
        }
        
        nouveauGroupe.mettreAjourLiensPieces();
    }

    /**
     * Permet de récupérer la liste des pièces autour d'une position
     * @param pos la position autour de laquelle on veut trouver les pieces
     * @return la liste des pieces trouvées
     */
    public List<Piece> getPiecesAutourDe(Position pos){
        int x  = pos.getX();
        int y = pos.getY();

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
