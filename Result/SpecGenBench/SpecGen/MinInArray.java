class MinInArray {
    //@ requires a != null;
    //@ ensures a.length == 0 ==> \result == -1;
 
    //@ ensures a.length > 0 ==> (\forall int j; 0 <= j && j < a.length; \result <= a[j]);
    public int minElementInArray(int[] a) {
        if (a.length == 0) return -1;
        int res = Integer.MIN_VALUE;
        //@ maintaining 0 <= i && i <= a.length;
 
        //@ maintaining (\forall int j; 0 <= j && j < i; res <= a[j]);
        //@ decreases a.length - i;
        for(int i = 0; i < a.length; i++) {
            res = ((a[i] < res) ? a[i] : res);
        }
        return res;
    }
}
