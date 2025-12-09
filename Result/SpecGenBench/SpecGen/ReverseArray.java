public class ReverseArray {
    //@ requires original != null;
    //@ ensures \result != null;
    //@ ensures \result.length == original.length;
    //@ ensures (\forall int i; 0 <= i && i < original.length; \result[i] == \old(original[original.length - 1 - i]));
    //@ ensures (\forall int i; 0 <= i && i < original.length; original[i] == \old(original[i]));
    //@ ensures \result != original;
    /*@ spec_public @*/ public static int[] reverse(int[] original) {
        int n = original.length;
        int[] res = new int[n];
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining res != null && res.length == n;
        //@ maintaining (\forall int j; 0 <= j && j < i; res[j] == original[n - 1 - j]);
        //@ decreases n - i;
        for(int i = 0; i < n ; i++) {
            res[i] = original[n - i - 1];
        }
        return res;
    }
}