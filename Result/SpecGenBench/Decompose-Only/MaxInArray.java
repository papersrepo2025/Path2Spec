
class MaxInArray {
    //@ requires a != null && a.length == 0;
    //@ ensures \result == -1;
    //@ also
    //@ requires a != null && 0 < a.length;
    //@ ensures (\exists int k; 0 <= k && k < a.length; \result == a[k]);
    //@ ensures (\forall int k; 0 <= k && k < a.length; a[k] <= \result);
    //@ also
    //@ requires a == null;
    //@ signals (NullPointerException e) true;
    public int maxElementInArray(int[] a) {
        if (a.length == 0) return -1;
        int res = Integer.MIN_VALUE;
        //@ maintaining 0 <= i && i <= a.length;
        //@ maintaining (\forall int k; 0 <= k && k < i; res >= a[k]);
        //@ maintaining (i == 0 ==> res == Integer.MIN_VALUE);
        //@ maintaining (i > 0 ==> (\exists int k; 0 <= k && k < i; res == a[k]));
        //@ decreases a.length - i;
        for(int i = 0; i < a.length; i++) {
            //@ assume i != 0;
            res = ((a[i] > res) ? a[i] : res);
        }
        return res;
    }
}