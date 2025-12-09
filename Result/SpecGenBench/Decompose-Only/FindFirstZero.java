
public class FindFirstZero {

    //@ assignable \nothing;
    //@ requires x == null;
    //@ signals (NullPointerException) true;
    //@ also
    //@ requires x != null && x.length == 0;
    //@ ensures \result == -1;
    //@ also
    //@ requires x != null && x.length > 0 && (\exists int k; 0 <= k && k < x.length; x[k] == 0);
    //@ ensures 0 <= \result && \result < x.length && x[\result] == 0 && (\forall int j; 0 <= j && j < \result; x[j] != 0);
    //@ also
    //@ requires x != null && x.length > 0 && (\forall int k; 0 <= k && k < x.length; x[k] != 0);
    //@ ensures \result == -1;
    public static int FindFirstZero(int[] x) {
        assert x.length >= 0;
        if (x.length == 0) {
            return -1;
        } else {
            //@ assert x != null && x.length >= 0;
            int index = 0;

            //@ loop_invariant 0 <= index && index <= x.length;
            //@ loop_invariant (\forall int j; 0 <= j && j < index; x[j] != 0);
            //@ decreases x.length - index;
            while (x.length - index > 0 && x[index] != 0) {
                //@ assert 0 <= index && index < x.length;
                //@ assert x[index] != 0;
                //@ assert index + 1 <= x.length;
                index = index + 1;
            }
            if (x.length - index == 0) {
                //@ assert x.length - index == 0;
                index = -1;
            }
            return index;
        }
    }
}