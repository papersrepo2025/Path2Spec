
public class FindFirstZero {
    //@ requires x != null;
    //@ ensures x.length == 0 ==> \result == -1;
    //@ also
    //@ requires x != null;
    //@ requires x.length > 0;
    //@ requires (\forall int i; 0 <= i && i < x.length; x[i] != 0);
    //@ ensures \result == -1;
    //@ also
    //@ requires x != null;
    //@ ensures (\exists int i; 0 <= i && i < x.length && x[i] == 0; \result == i) || \result == -1;
    public static int FindFirstZero(int[] x) {
        assert x.length >= 0;
        if (x.length == 0) {
            return -1;
        } else {
            int index = 0;

            //@ maintaining 0 <= index && index <= x.length;
            //@ decreases x.length - index;
            while (x.length - index > 0 && x[index] != 0) {
                index = index + 1;
            }
            if (x.length - index == 0) {
                index = -1;
            }
            return index;
        }
    }
}
