/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author oriane école
 */
public class Case extends JLabel{
        private int posX;
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
        
    public Case (int x, int y){
        super(new ImageIcon("case-vide.png"));
        this.setPosX(x);
        this.setPosY(y);
        this.setBounds(0, 0, 25, 25);
        this.setPreferredSize(new java.awt.Dimension(25, 25));
        this.setMaximumSize(new java.awt.Dimension(25, 25));
        this.setMinimumSize(new java.awt.Dimension(25, 25));    
    }
    
    public void setAffichage(boolean couleur){
        if (couleur) {
            this.setIcon((Icon) new ImageIcon("case-blanc.png"));
        } else {
            this.setIcon((Icon) new ImageIcon("case-noir.png"));
            
        }
    }
    
    
    }
