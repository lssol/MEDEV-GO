/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

import java.io.Serializable;
import jeugo.exceptions.PasDePlateaudeCetteTaille;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe pour décrire un plateau de jeu
 *
 * @author Sacha
 */
public final class PlateauDeJeu implements Serializable {
    /*
     * Matrice des pièces sur le plateau de jeu
     */

    public Piece pieces[][];
    /**
     * Taille du plateau de jeu
     */
    private int width;
    /**
     * Nom du joueur blanc
     */
    private String jBlanc;
    /**
     * Nom du joueur noir
     */
    private String jNoir;
    /**
     * Nombre de pièces capturées par le joueur blanc
     */
    private int nbPrisonBlanc;
    /**
     * Nombre de pièces capturées par le joueur noir
     */
    private int nbPrisonNoir;
    /**
     * Nombre de pierres noires de handicap
     */
    private int handicap;
    /**
     * Liste des tailles de plateau acceptées
     */
    private List taillesOk;

    /**
     * Liste de groupes, utilisée pour la suppression des pièces sans libertés
     */
    private ArrayList<Groupe> groupesTampon;

    public Piece[][] getPieces() {
        return pieces;
    }

    public int getWidth() {
        return width;
    }

    public String getjBlanc() {
        return jBlanc;
    }

    public String getjNoir() {
        return jNoir;
    }

    public void setHandicap(int i) {
        this.handicap = i;
    }

    int getHandicap() {
        return handicap;
    }

    public void setjBlanc(String jBlanc) {
        this.jBlanc = jBlanc;
    }

    public void setjNoir(String jNoir) {
        this.jNoir = jNoir;
    }

    public int getNbPrisonBlanc() {
        return nbPrisonBlanc;
    }

    public void setNbPrisonBlanc(int nbPrisonBlanc) {
        this.nbPrisonBlanc = nbPrisonBlanc;
    }

    public int getNbPrisonNoir() {
        return nbPrisonNoir;
    }

    public void setNbPrisonNoir(int nbPrisonNoir) {
        this.nbPrisonNoir = nbPrisonNoir;
    }

    /**
     * Constructeur de recopie
     *
     * @param pl un PlateauDeJeu
     * @throws PasDePlateaudeCetteTaille
     */
    public PlateauDeJeu(PlateauDeJeu pl) throws PasDePlateaudeCetteTaille {
        this.handicap = pl.handicap;
        this.jBlanc = pl.jBlanc;
        this.jNoir = pl.jNoir;
        this.pieces = pl.pieces;
        this.nbPrisonBlanc = pl.nbPrisonBlanc;
        this.nbPrisonNoir = pl.nbPrisonNoir;
        this.width = pl.width;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces.length; j++) {
                this.pieces[i][j] = pl.pieces[i][j];
            }
        }
        this.taillesOk = new ArrayList(pl.taillesOk);
    }

    /**
     * Constructeur qui crée un plateau de la taille indiquée dans width, qui
     * refuse de le créer si la taille ne fait pas partie des tailles acceptées
     *
     * @param width un entier correspondant à la taille du goban
     * @throws jeugo.exceptions.PasDePlateaudeCetteTaille
     */
    public PlateauDeJeu(int width) throws PasDePlateaudeCetteTaille {
        // initialisation des attributs
        this.taillesOk = Arrays.asList(9, 16, 19);

        if (!taillesOk.contains((Integer) width)) {
            throw new PasDePlateaudeCetteTaille(width);
        } else {
            this.width = width;
            pieces = new Piece[width][width];
        }
        this.groupesTampon = new ArrayList<>();
    }

    /**
     * Méthode qui supprime les groupes qui n'ont plus de libertés Seuls les
     * groupes qui sont dans le tampon sont analysés, ce qui permet d'eviter de
     * parcourir tous les groupes (Remarque : pas besoin de transmettre la
     * couleur, grâce au systeme de tampon)
     */
    private void supprimerGroupes() {
        for (Groupe groupe : groupesTampon) {
            if (!groupe.aLiberte(this)) {
                int nbPiecesSuppr = groupe.supprimer(this);
                if (groupe.get(0).getCouleur()) {
                    this.nbPrisonNoir = this.nbPrisonNoir + nbPiecesSuppr;
                } else {
                    this.nbPrisonBlanc = this.nbPrisonBlanc + nbPiecesSuppr;
                }
            }
        }
    }

    /**
     * Méthode qui modélise un tour de jeu d'un joueur
     *
     * Gestion du choix de la position, de la vérification de la position, de
     * l'insertion et de la suppression des groupes
     *
     * @param pos la position à laquelle le joueur veut jouer
     * @param couleur est la couleur qui joue : true = blanc
     * @return un String correspondant à un message de sortie ("ok" ou "position
     * impossible")
     */
    public String jouer(Position pos, boolean couleur) {
        String res;
        // demander la position ou placer la pièce
        // vérifier la position : tant que la position incorrecte - redemander 
        if (this.verifierPosition(pos, couleur)) {
            this.insererPiece(pos, couleur);
            res = "ok";
            // enregister l'état du plateau dans historique
            // enregister le mouvement dans le fichier texte
            //historique.sauvegarde(this);
            return res;
        } else {
            res = "position impossible";
            return res;
        }
    }

    /**
     * Méthode qui vérifie la conformité d'une position choisie avec les règles
     * du GO
     *
     * @param p la position a tester
     * @param couleur le joueur en train de poser une pièce
     * @return un booléen (true si la position est compatible avec les règles)
     */
    public boolean verifierPosition(Position p, boolean couleur) {
        // position dns les limites du plateau
        if (p.getX() < width && p.getY() < width && p.getX() >= 0 && p.getY() >= 0) {
            //position libre
            if (pieces[p.getX()][p.getY()] == null) {
                Piece tmp = new Piece(couleur, p);
                pieces[p.getX()][p.getY()] = tmp;
                // regle de Ko
//                if (!this.historique.existe(pieces)) {
                // a des libertés
                if (!tmp.getibertes(this).isEmpty()) {
                    pieces[p.getX()][p.getY()] = null;
                    return true;
                } else {
                    // suicide ou bien suppression d'un groupe tampon
                    for (Piece piece : getPiecesAutourDe(p)) {
                        if (piece != null) {
                            if (piece.getCouleur() == !couleur) {
                                if (!piece.getGroupe().aLiberte(this)) {
                                    pieces[p.getX()][p.getY()] = null;
                                    return true;
                                }
                            }
                        }
                    }
                    // si pour les quatre voisins, aucun groupe adversaire n'est privé de ses libertés alors c'est un suicide
                    pieces[p.getX()][p.getY()] = null;
                    String err = "Suicide : Position impossible";
                    //vue.afficherErreur(err);
                    return false;
                }
//                } else {
//                    // configuration déjà vue
//                    pieces[p.getX()][p.getY()] = null;
//                    vue.afficherErreur("Règle du Ko : Configuration déjà rencontrée");
//                    return false;
//                }

            } else {
                // position deja prise
                String err = "Position déjà occupée";
                //vue.afficherErreur(err);
                return false;
            }
        } else {
            // position hors limite
            String err = "Position hors limite";
            //vue.afficherErreur(err);
            return false;
        }
    }

    /**
     * Méthode qui positionne une pièce de couleur donnée à une position donnée
     * la position est vérifié au préalable les groupes sont mis à jour en
     * fonction de la nouvelle pièce
     *
     * @param pos la position de la pièce à insérer
     * @param couleur la couleur de la pièce positionnée
     */
    public void insererPiece(Position pos, boolean couleur) {
        // réinitialisation du tampon
        groupesTampon = new ArrayList<>();

        this.pieces[pos.getX()][pos.getY()] = new Piece(couleur, pos);
        Groupe nouveauGroupe = new Groupe();
        nouveauGroupe.add(this.pieces[pos.getX()][pos.getY()]);

        // parcours des pièces autour de la position
        // quand on tombe sur une piece de même couleur,
        // on ajoute son groupe au nouveau groupe, puis on supprime son groupe
        for (Piece piece : getPiecesAutourDe(pos)) {
            if (piece.getCouleur() == couleur) {
                if (!nouveauGroupe.contains(piece)) {
                    nouveauGroupe.addAll(piece.getGroupe());
                }
            } else {
                if (!groupesTampon.contains(piece)) {
                    groupesTampon.add(piece.getGroupe());
                }
            }
        }
        nouveauGroupe.mettreAjourLiensPieces();
        this.supprimerGroupes();
    }

    /**
     * Méthode qui permet de récupérer la liste des pièces autour d'une position
     *
     * @param pos la position autour de laquelle on veut trouver les pieces
     * @return la liste des pieces trouvées
     */
    public List<Piece> getPiecesAutourDe(Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        List<Piece> liste = new ArrayList<>();
        if (pieces.length != x) {
            if (pieces[x + 1][y] != null) {
                liste.add(pieces[x + 1][y]);
            }
        }
        if (pieces.length != y) {
            if (pieces[x][y + 1] != null) {
                liste.add(pieces[x][y + 1]);
            }
        }
        if (x != 0) {
            if (pieces[x - 1][y] != null) {
                liste.add(pieces[x - 1][y]);
            }
        }

        if (y != 0) {
            if (pieces[x][y - 1] != null) {
                liste.add(pieces[x][y - 1]);
            }
        }

        return liste;
    }

}
