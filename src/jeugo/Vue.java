package jeugo;

import java.util.Scanner;

import static jeugo.PlateauDeJeu.pieces;

/**
 * Created by seti on 03/12/15.
 */
public class Vue {
    public Vue() {
    }
    public void afficherPlateau(){
        System.out.println("DÉBUT AFFICHAGE DU PLATEAU DE JEU");
        for(int i=0; i < pieces[0].length; i++) {
            String chaine = "";
            for(int j=0; j < pieces[0].length; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].getCouleur()) {
                        chaine = chaine + "B";
                    } else {
                        chaine = chaine + "N";
                    }
                } else {
                    chaine = chaine + "*";
                }
            }
            System.out.println(chaine);
        }
        System.out.println("FIN AFFICHAGE DU PLATEAU DE JEU");
    }
    public Position demanderPosition(){
        Position pos = new Position();
        Scanner sc = new Scanner(System.in);
        System.out.println("Où souhaitez-vous poser votre pièce ?");
        System.out.println("Sur quelle ligne ?");
        String ligne = sc.nextLine();
        try {
            pos.setX(Integer.parseInt(ligne));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        System.out.println("Sur quelle colonne ?");
        String colonne = sc.nextLine();
        try {
            pos.setY(Integer.parseInt(colonne));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return pos;
    }
    public void afficherInformation(String info){
        System.out.println(info);
    }

    /**
     * Demande les noms des joueurs aux joueurs et les renvois
     * @return La première case est le nom du blanc, la seconde, le nom du noir
     */
    public String[] demanderNomsJoueurs(){
        String[] s = new String[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Indiquez le nom du 1er joueur");
        s[0] = sc.nextLine();
        System.out.println("Indiquez le nom du 2ème joueur");
        s[1] = sc.nextLine();
        return s;
    }
    
    public void afficherTourJoueur(boolean couleur){
        String nom = "";
        if (couleur){
            nom = "Blanc : "+PlateauDeJeu.getjBlanc();
        } else {
            nom = "Noir : "+PlateauDeJeu.getjNoir();
        }
        System.out.println("Tour du joueur "+nom);
    }
    
    public void afficherErreur(String err){
        System.out.println("** "+err+" **");
    }
}
