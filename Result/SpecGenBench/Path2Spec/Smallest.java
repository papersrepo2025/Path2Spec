
public class Smallest {

    //@ requires a != null;
//@ ensures a.length == 0 ==> \result == -1;
//@ ensures a.length > 0 ==> 0 <= \result && \result < a.length;
    static public int Smallest(int[] a) {
        if (a.length == 0) return -1;

        int index = 0;
        int smallest = 0;

        //@ loop_invariant 0 <= index && index <= a.length;
//@ loop_invariant 0 <= smallest && smallest < a.length;
//@ decreases a.length - index;
        while (a.length - index > 0) {
            //@ requires 0 <= index && index < a.length;
            //@ requires 0 <= smallest && smallest < a.length;
            if (a[index] < a[smallest]) {
                smallest = index;
            }
            index = index + 1;
        }
        return smallest;
    }
}
