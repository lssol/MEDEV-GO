package jeugo.exceptions;

/**
 * Created by seti on 03/12/15.
 */
public class PasDePlateaudeCetteTaille extends Exception {
    public PasDePlateaudeCetteTaille(int width) {
        super("Le plateau ne peut pas faire " + width + "x" + width);
    }


}
