public class Biggest {

    //@ requires a != null;
    //@ ensures a.length == 0 ==> \result == -1;
    //@ ensures a.length > 0 ==> (0 <= \result && \result < a.length);
    //@ ensures a.length > 0 ==> (\forall int i; 0 <= i && i < a.length; a[\result] >= a[i]);
    //@ ensures a.length > 0 ==> (\forall int j; 0 <= j && j < \result; a[j] < a[\result]);
    /*@ pure @*/ static public int biggest(int[] a) {
        if (a.length == 0) return -1;

        int index = 0;
        int biggest = 0;

        //@ maintaining 0 <= index && index <= a.length;
        //@ maintaining (index == 0 ==> biggest == 0);
        //@ maintaining (index > 0 ==> 0 <= biggest && biggest < index);
        //@ maintaining (index > 0 ==> (\forall int j; 0 <= j && j < index; a[biggest] >= a[j]));
        //@ maintaining (index > 0 ==> (\forall int j; 0 <= j && j < biggest; a[j] < a[biggest]));
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