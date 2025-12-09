

public class ReverseArray {
    //@ requires original != null;
    //@ ensures \result != null && \result.length == \old(original.length);
    //@ ensures (\forall int k; 0 <= k && k < \result.length; \result[k] == \old(original[\old(original.length) - k - 1]));
    //@ also
    //@ requires original == null;
    //@ signals (NullPointerException) original == null;
    public static int[] reverse(int[] original) {
        int n = original.length;
        int[] res = new int[n];
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining res.length == n && original.length == n;
        //@ maintaining (\forall int k; 0 <= k && k < i; res[k] == original[n - k - 1]);
        //@ decreases n - i;
        for(int i = 0; i < n ; i++) {
            //@ assume i != 0;
            res[i] = original[n - i - 1];
        }
        return res;
    }
}