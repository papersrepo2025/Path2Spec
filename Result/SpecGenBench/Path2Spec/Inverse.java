
public class Inverse {
    //@ requires x != null && y != null;
    //@ requires x.length != y.length;
    //@ ensures \result == false;
    //@ also
    //@ requires x != null && y != null;
    //@ requires x.length == y.length && x.length > 0;
    //@ requires (\forall int i; 0 <= i && i < x.length; x[i] == y[x.length - 1 - i]);
    //@ ensures \result == true;
    public static boolean Inverse(int[] x, int[] y) {
        if (x.length != y.length) return false;
        int index = 0;

        //@ maintaining 0 <= index && index <= x.length;
        //@ decreases x.length - index;
        while (index < x.length) {
            if (x[index] != y[x.length - 1 - index]) {
                return false;
            } else {
                index = index + 1;
            }
        }
        return true;
    }
}
