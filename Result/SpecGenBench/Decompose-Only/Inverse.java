public class Inverse {

    //@ requires x != null;
    //@ requires y != null;
    //@ requires x.length != y.length;
    //@ ensures \result == false;
    //@ also
    //@ requires x != null;
    //@ requires y != null;
    //@ requires x.length == y.length && (\forall int k; 0 <= k && k < x.length; x[k] == y[x.length - 1 - k]);
    //@ ensures \result == true;
    //@ also
    //@ requires x != null;
    //@ requires y != null;
    //@ requires x.length == y.length && (\exists int k; 0 <= k && k < x.length; x[k] != y[x.length - 1 - k]);
    //@ ensures \result == false;
    public static boolean Inverse(int[] x, int[] y) {
        if (x.length != y.length) return false;
        int index = 0;

        //@ maintaining x != null && y != null;
        //@ maintaining x.length == y.length;
        //@ maintaining 0 <= index && index <= x.length;
        //@ maintaining (\forall int i; 0 <= i && i < index; x[i] == y[x.length - 1 - i]);
        //@ maintaining index < x.length ==> (0 <= x.length - 1 - index && x.length - 1 - index < y.length);
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