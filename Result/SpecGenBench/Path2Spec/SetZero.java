
public class SetZero {

    //@ requires a != null;
//@ requires 0 <= iBegin && iBegin <= a.length;
//@ requires 0 <= iEnd && iEnd <= a.length;
//@ requires iBegin >= iEnd; // Path 1: Empty or invalid range;
//@ ensures (\forall int i; 0 <= i && i < a.length; a[i] == \old(a[i])); // No changes to array;
    public static void SetZero(int[] a, int iBegin, int iEnd) {
        int k = iBegin;

        //@ loop_invariant k == iBegin;
//@ loop_invariant (\forall int i; 0 <= i && i < a.length; a[i] == \old(a[i]));
//@ decreases iEnd - k;
        while (k < iEnd) {
            a[k] = 0;
            k = k + 1;
        }
    }
}
