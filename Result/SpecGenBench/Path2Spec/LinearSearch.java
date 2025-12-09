
public class LinearSearch {

    private static int location;
    //@ requires array != null;
    //@ ensures (\result == -1) <==> (\forall int i; 0 <= i < array.length; array[i] != search);
    //@ ensures (\result != -1) ==> (0 <= \result < array.length && array[\result] == search);
    //@ also
    //@ requires array != null;
    //@ requires array.length > 0;
    //@ ensures (\exists int i; 0 <= i && i < array.length; array[i] == search) ==> \result >= 0 && \result < array.length && array[\result] == search;
    //@ ensures (\forall int i; 0 <= i && i < \result; array[i] != search);
    //@ ensures !(\exists int i; 0 <= i && i < array.length; array[i] == search) ==> \result == -1;
    //@ also
    //@ requires array != null;
    //@ requires array.length > 0;
    //@ requires (\forall int i; 0 <= i && i < array.length; array[i] != search);
    //@ ensures \result == -1;
    public static int linearSearch(int search, int array[]) {
        int c;

        //@ loop_invariant 0 <= c && c <= array.length;
        //@ loop_invariant (\forall int i; 0 <= i < c; array[i] != search);
        //@ decreases array.length - c;
        for (c = 0; c < array.length; c++) {
            if (array[c] == search) {
                location = c;
                break;
            }
        }

        if (c == array.length) {
            location = -1;
        }
        return location;
    }
}
