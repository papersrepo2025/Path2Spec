class SmallestEvenMulBranch {
    /*@ 
      @ requires 0 <= n && n <= Integer.MAX_VALUE / 2;
      @ ensures (n % 2 == 0 ==> \result == n) && (n % 2 != 0 ==> \result == 2 * n);
      @ ensures \result % 2 == 0;
      @ ensures (\exists int k; \result == k * n);
      @ ensures \result >= 0;
      @ ensures (\forall int m; 0 < m && m % 2 == 0 && (\exists int k; m == k * n); \result <= m);  
      @*/
    /*@ spec_public @*/ public int smallestEvenMultiple(int n) {
        if(n % 2 == 0) {
            return n;
        }
        return 2 * n;
    }
}
