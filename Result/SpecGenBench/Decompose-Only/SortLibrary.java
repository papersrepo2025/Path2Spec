

import java.util.Arrays;

public class SortLibrary {

    //@ requires arr != null;
    //@ assignable arr[*];
    //@ ensures arr == \old(arr);
    //@ ensures arr.length == \old(arr.length);
    //@ ensures (\forall int i; 1 <= i && i < arr.length; arr[i - 1] <= arr[i]);
    //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < arr.length; arr[i] == v)
    //@                      == (\num_of int i; 0 <= i && i < arr.length; \old(arr[i]) == v));
    //@ also
    //@ requires arr == null;
    //@ assignable \nothing;
    //@ signals (NullPointerException e) true;
    public static void sort(int[] arr) {
        Arrays.sort(arr);
    }
}