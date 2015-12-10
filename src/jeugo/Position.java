/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeugo;

/**
 * Une position sur le plateau
 * @author oriane école
 */
public class Position {
    private int x;
    private int y;
    
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public Position (){
       this.x = 0;
       this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
   
    public boolean equals (Position p){
        return this.x == p.x && this.y == p.y;
    }
}
