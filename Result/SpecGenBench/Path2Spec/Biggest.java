
public class Biggest {
    //@ requires a != null && a.length > 0;
    //@ ensures 0 <= \result && \result < a.length;
    //@ ensures (\forall int i; 0 <= i && i < a.length; a[\result] >= a[i]);
    //@ also
    //@ requires a != null;
    //@ ensures a.length == 0 ==> \result == -1;
    static public int biggest(int[] a) {
        if (a.length == 0) return -1;

        int index = 0;
        int biggest = 0;

        //@ maintaining 0 <= index && index <= a.length;
        //@ maintaining 0 <= biggest && biggest < a.length;
        //@ maintaining (\forall int i; 0 <= i && i < index; a[biggest] >= a[i]);
        //@ decreases a.length - index;
        while (a.length - index > 0) {
            if (a[index] > a[biggest]) {
                biggest = index;
            }
            index = index + 1;
        }
        return biggest;
    }
}
