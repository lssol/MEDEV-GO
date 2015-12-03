package jeugo.exceptions;

/**
 * Created by seti on 03/12/15.
 */
public class AhYaDejaQuelquUnIci extends Exception {
    public AhYaDejaQuelquUnIci(int[] pos) {
        super("Désolé, mais il y a déjà une pièce à la position (" + pos[0] + ", " + pos[1] + ")");
    }
}
