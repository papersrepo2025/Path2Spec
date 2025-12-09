class IsOneBitCharacter {
    /*@
      @ requires bits != null;
      @ requires bits.length >= 1;
      @ requires (\forall int k; 0 <= k && k < bits.length; bits[k] == 0 || bits[k] == 1);
      @ requires bits[bits.length - 1] == 0;
      @
      @ // The result equals the parity of the number of consecutive 1s
      @ // immediately preceding the final 0 (true iff that count is even).
 
      @            0 <= c && c <= bits.length - 1
      @            && (\forall int t; 0 <= t && t < c; bits[bits.length - 2 - t] == 1)
      @            && (bits.length - 2 - c < 0 || bits[bits.length - 2 - c] == 0)
      @            && \result == (c % 2 == 0));
      @*/
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        /*@
          @ maintaining n == bits.length;
          @ maintaining 1 <= n;
          @ maintaining bits[n-1] == 0;
          @ maintaining 0 <= i && i <= n;
          @ maintaining (\forall int k; 0 <= k && k < n; bits[k] == 0 || bits[k] == 1);
          @ decreases n - 1 - i;
          @*/
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }
}
