package jeugo.exceptions;

import jeugo.Position;

/**
 * Created by seti on 03/12/15.
 */
public class AhYaDejaQuelquUnIci extends Exception {
    public AhYaDejaQuelquUnIci(Position pos) {
        super("Désolé, mais il y a déjà une pièce à la position (" + pos.getX() + ", " + pos.getY() + ")");
    }
}
