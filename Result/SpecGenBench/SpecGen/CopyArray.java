public class CopyArray {
 
    /*@
      @ public normal_behavior
      @ requires b != null && a != null;
      @ requires 0 <= iBegin && iBegin <= iEnd;
      @ requires iEnd <= b.length && iEnd <= a.length;
      @ ensures (\forall int i; 0 < i && i < a.length && (i < iBegin && i > iEnd); a[i] == \old(a[i]));  
 
 
      @*/
    /*@ spec_public @*/ public static void CopyArray(int[] b, int iBegin, int iEnd, int[] a) {
        int k = iBegin;

        /*@
          @ maintaining iBegin <= k && k <= iEnd;
 
          @ maintaining b != null && a != null && 0 <= iBegin && iBegin <= iEnd && iEnd <= b.length && iEnd <= a.length;
          @ decreases iEnd - k;
          @*/
        while (iEnd - k > 0) {
            a[k] = b[k];
            k = k + 1 ;
        }
    }
}
