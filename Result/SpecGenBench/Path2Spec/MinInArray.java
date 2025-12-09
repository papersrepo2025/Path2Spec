
class MinInArray {
    //@ requires a != null;
//@ requires a.length >= 1;
//@ ensures (\exists int i; 0 <= i && i < a.length; a[i] == \result);
//@ ensures (\forall int i; 0 <= i && i < a.length; a[i] >= \result);
    public int minElementInArray(int[] a) {
        int res = a[0];
        //@ loop_invariant 1 <= i && i <= a.length;
//@ loop_invariant (\exists int j; 0 <= j && j < i; a[j] == res);
//@ loop_invariant (\forall int j; 0 <= j && j < i; a[j] >= res);
//@ decreases a.length - i;
        for(int i = 1; i < a.length; i++) {
            res = ((a[i] < res) ? a[i] : res);
        }
        return res;
    }
}
