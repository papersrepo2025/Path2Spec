
class MaxInArray {
    //@ requires a != null;
//@ ensures a.length == 0 ==> \result == -1;
    public int maxElementInArray(int[] a) {
        if (a.length == 0) return -1;
        int res = Integer.MIN_VALUE;
        //@ loop_invariant 0 <= i && i <= a.length;
//@ loop_invariant (\forall int j; 0 <= j && j < i; res >= a[j]);
//@ decreases a.length - i;
        for(int i = 0; i < a.length; i++) {
            res = ((a[i] > res) ? a[i] : res);
        }
        return res;
    }
}
