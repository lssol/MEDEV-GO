/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe pour décrire une case du goban
 *
 * @author oriane école
 */
public class Case extends JLabel {

    /**
     * Position X (ligne)
     */
    private int posX;
    /**
     * Position Y (colonne)
     */
    private int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Case(int x, int y) {
        super(new ImageIcon("case-vide.png"));
        this.posX = x;
        this.posY = y;
        this.setBounds(0, 0, 25, 25);
        this.setPreferredSize(new java.awt.Dimension(25, 25));
        this.setMaximumSize(new java.awt.Dimension(25, 25));
        this.setMinimumSize(new java.awt.Dimension(25, 25));
    }

    /**
     * Méthode qui change une case vide en une case avec une pierre
     *
     * @param couleur un booléen représentant la couleur de la pierre (true =
     * blanc)
     */
    public void setAffichage(boolean couleur) {
        if (couleur) {
            this.setIcon((Icon) new ImageIcon("case-blanc.png"));
        } else {
            this.setIcon((Icon) new ImageIcon("case-noir.png"));
        }
    }

    /**
     * Méthode qui change une case avec une pierre en une case vide
     */
    public void setVide() {
        this.setIcon((Icon) new ImageIcon("case-vide.png"));
    }

}
