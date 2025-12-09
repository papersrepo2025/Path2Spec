
public class CopyArray {
 
    //@ requires a != null && b != null;
    //@ requires 0 <= iBegin && iBegin <= iEnd;
    //@ requires iEnd <= a.length && iEnd <= b.length;
    //@ ensures (\forall int j; 0 <= j && j < b.length; b[j] == \old(b[j]));
    //@ ensures (\forall int j; 0 <= j && j < a.length; (j < iBegin || j >= iEnd) ==> a[j] == \old(a[j]));
    //@ ensures (\forall int j; iBegin <= j && j < iEnd; a[j] == \old(b[j]));
    public static void CopyArray(int[] b, int iBegin, int iEnd, int[] a) {
        int k = iBegin;

        //@ maintaining a != null && b != null;
        //@ maintaining 0 <= iBegin && iBegin <= k && k <= iEnd;
        //@ maintaining iEnd <= a.length && iEnd <= b.length;
        //@ maintaining (\forall int j; iBegin <= j && j < k; a[j] == \old(b[j]));
        //@ maintaining (\forall int j; 0 <= j && j < a.length; (j < iBegin || j >= iEnd) ==> a[j] == \old(a[j]));
        //@ maintaining (\forall int j; 0 <= j && j < b.length; b[j] == \old(b[j]));
        //@ maintaining iEnd - k >= 0;
        //@ decreasing iEnd - k;
        while (iEnd - k > 0) {
            a[k] = b[k];
            k = k + 1 ;
        }
    }
}