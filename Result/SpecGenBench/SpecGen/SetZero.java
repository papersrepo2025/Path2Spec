public class SetZero {

	/*@
	  @ requires a != null;
	  @ requires 0 <= iBegin && iBegin <= iEnd && iEnd <= a.length;
	  @ assignable a[iBegin .. iEnd-1];
	  @ ensures (\forall int j; 0 <= j && j < iBegin; a[j] == \old(a[j]));
	  @ ensures (\forall int j; iBegin <= j && j < iEnd; a[j] == 0);
	  @ ensures (\forall int j; iEnd <= j && j < a.length; a[j] == \old(a[j]));
	  @*/
	public static void SetZero(int[] a, int iBegin, int iEnd) {
		int k = iBegin;

		/*@
		  @ maintaining a != null && 0 <= iBegin && iBegin <= iEnd && iEnd <= a.length;
		  @ maintaining iBegin <= k && k <= iEnd;
		  @ maintaining (\forall int j; 0 <= j && j < iBegin; a[j] == \old(a[j]));
		  @ maintaining (\forall int j; iBegin <= j && j < k; a[j] == 0);
		  @ maintaining (\forall int j; iEnd <= j && j < a.length; a[j] == \old(a[j]));
		  @ decreases iEnd - k;
		  @*/
		while (k < iEnd) {
            		a[k] = 0;
            		k = k + 1 ;
        	}
	}
}