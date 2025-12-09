class GCD {
    /*@
      @ assignable \nothing;
      @ ensures (y1 <= 0 || y2 <= 0) ==> \result == 0;
      @ ensures (y1 > 0 && y2 > 0) ==> (
      @     \result > 0
      @  && y1 % \result == 0 && y2 % \result == 0
      @  && (\forall int z; z > 0 && y1 % z == 0 && y2 % z == 0; z <= \result)
      @  && \result <= y1 && \result <= y2
      @ );
      @*/
    static /*@ spec_public @*/ /*@ pure @*/ int gcd(int y1, int y2) {
        if (y1 <= 0 || y2 <= 0) {
          return 0;
        }
        if (y1 == y2) {
          return y1;
        }
        if (y1 > y2) {
          return gcd(y1 - y2, y2);
        }
        return gcd(y1, y2 - y1);
    }
}