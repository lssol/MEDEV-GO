/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;
import jeugo.Enregistrement;
import jeugo.PlateauDeJeu;
import jeugo.Position;
import jeugo.exceptions.PasDePlateaudeCetteTaille;

/**
 * Classe pour décrire l'interface graphique de jeu
 *
 * @author oriane école
 */
public class Goban extends javax.swing.JFrame {

    // attributs permettant de gérer l'affichage
    private java.awt.Panel mainPanel;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.TextField message1;
    private java.awt.TextField message2;
    private java.awt.TextField message3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private Case[][] cases;

    public void setPlateauDeJeu(PlateauDeJeu p) throws PasDePlateaudeCetteTaille {
        this.pl = new PlateauDeJeu(p);
    }

    public PlateauDeJeu getPlateauDeJeu() {
        return pl;
    }

    /**
     * Plateau de jeu
     */
    private PlateauDeJeu pl;
    /**
     * Booléen indiquant quel joueur doit jouer (true = blanc)
     */
    private boolean tour;

    /**
     * Constructeur sans paramètre, qui initialise les attributs cases et tour
     */
    public Goban() {
        cases = new Case[19][19];
        tour = true;
    }

    /**
     * Méthode qui initialise l'affichage
     *
     * @param taille un entier représentant la taille du goban
     */
    public void initAffichage(int taille) {

        // panel n°1 avec le goban
        panel1 = new Panel();
        GridLayout grille1 = new GridLayout(taille, taille, 0, 0);
        panel1.setLayout(grille1);
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                cases[i][j] = new Case(i, j);
                cases[i][j].setVisible(true);
                cases[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        caseMouseClicked(evt);
                    }
                });
                panel1.add(cases[i][j]);
            }
        }
        panel1.setSize(25 * taille, 25 * taille);

        // panel n°2 avec un message indiquant le joueur courant ou une erreur,
        // et l'affichage des points
        panel2 = new Panel();
        GridLayout grille2 = new GridLayout(8, 1, 0, 0);
        panel2.setLayout(grille2);

        label1 = new Label();
        label1.setText("Message");
        panel2.add(label1);
        message1 = new TextField();
        message1.setEditable(false);
        message1.setColumns(25);
        message1.setText("Tour du joueur Blanc : " + pl.getjBlanc());
        panel2.add(message1);

        label2 = new Label();
        label2.setText("Points Blanc");
        panel2.add(label2);
        message2 = new TextField();
        message2.setEditable(false);
        message2.setColumns(25);
        message2.setText("0");
        panel2.add(message2);

        label3 = new Label();
        label3.setText("Points Noir");
        panel2.add(label3);
        message3 = new TextField();
        message3.setEditable(false);
        message3.setColumns(25);
        message3.setText("0");
        panel2.add(message3);

        // panel n°3 avec des boutons pour sauvegarder la partie, charger une partie, 
        // passer son tour
        panel3 = new Panel();
        GridLayout grille3 = new GridLayout(6, 1, 0, 0);
        panel3.setLayout(grille3);
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Passer son tour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passerTourActionPerformed(evt);
            }
        });
        panel3.add(jButton1);
        jButton2 = new javax.swing.JButton();
        jButton2.setText("Sauvegarder la partie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauvegarderActionPerformed(evt);
            }
        });
        panel3.add(jButton2);
        jButton3 = new javax.swing.JButton();
        jButton3.setText("Arrêter la partie");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arreterActionPerformed(evt);
            }
        });
        panel3.add(jButton3);

        // panel global
        mainPanel = new Panel();
        GridLayout grille = new GridLayout(1, 3, 0, 0);
        mainPanel.setLayout(grille);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        // propriétés de la fenêtre
        setContentPane(mainPanel);
        setSize(75 * taille, 25 * taille);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Méthode pour gérer le clic sur une case du goban
     *
     * @param evt un événement
     */
    private void caseMouseClicked(java.awt.event.MouseEvent evt) {
        Position pos = new Position(((Case) evt.getSource()).getPosX(), ((Case) evt.getSource()).getPosY());
        String res = pl.jouer(pos, tour);
        if ("ok".equals(res)) {
            // on met à jour l'affichage du goban
            this.updatePlateau();
            // on inverse le booléen tour
            tour = !tour;
            if (tour) {
                message1.setText("Tour du joueur Blanc : " + pl.getjBlanc());
            } else {
                message1.setText("Tour du joueur Noir : " + pl.getjNoir());
            }
            // on met à jour l'affichage du nombre de points
            message2.setText("" + pl.getNbPrisonBlanc());
            message3.setText("" + pl.getNbPrisonNoir());
        } else {
            message1.setText(res);
        }

    }

    /**
     * Méthode pour gérer le clic sur le bouton de sauvegarde
     *
     * @param evt un événement
     */
    private void sauvegarderActionPerformed(java.awt.event.ActionEvent evt) {
        Date date = new Date();
        SimpleDateFormat dateFormatComp;
        dateFormatComp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        String nomFichier = "sauvegarde_" + dateFormatComp.format(date);
        Enregistrement.enregistrer(nomFichier, pl);
    }

    /**
     * Méthode pour gérer le clic sur le bouton de passage de tour
     *
     * @param evt un événement
     */
    private void passerTourActionPerformed(java.awt.event.ActionEvent evt) {
        tour = !tour;
        if (tour) {
            message1.setText("Tour du joueur Blanc : " + pl.getjBlanc());
        } else {
            message1.setText("Tour du joueur Noir : " + pl.getjNoir());
        }
    }

    /**
     * Méthode pour gérer le clic sur le bouton d'arrêt
     *
     * @param evt un événément
     */
    private void arreterActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /**
     * Méthode pour gérer la mise à jour de l'affichage du goban
     */
    public void updatePlateau() {
        for (int i = 0; i < this.pl.getWidth(); i++) {
            for (int j = 0; j < this.pl.getWidth(); j++) {
                if (this.pl.pieces[i][j] != null) {
                    cases[i][j].setAffichage(this.pl.pieces[i][j].getCouleur());
                } else {
                    cases[i][j].setVide();
                }
            }
        }
    }

    /**
     * Méthode pour mettre à jour l'affichage lors du chargement d'une partie
     */
    public void chargementPartie() {
        this.updatePlateau();
        if (tour) {
            message1.setText("Tour du joueur Blanc : " + this.pl.getjBlanc());
        } else {
            message1.setText("Tour du joueur Noir : " + this.pl.getjNoir());
        }
        message2.setText("" + this.pl.getNbPrisonBlanc());
        message3.setText("" + this.pl.getNbPrisonNoir());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Goban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Goban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Goban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Goban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Goban().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
