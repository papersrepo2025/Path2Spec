
class NumberOfCutsSeq {
    /*@ 
      @ requires n == 1;
      @ ensures \result == 0;
      @ also
      @ requires n != 1 && n % 2 == 0;
      @ ensures \result == n / 2;
      @ also
      @ requires n != 1 && n % 2 != 0;
      @ ensures \result == n;
      @*/
    public /*@ pure @*/ int numberOfCuts(int n) {
        //@ assert 2 != 0;
        return ((n == 1) ? 0 : ((n % 2 == 0) ? (n / 2) : n));
    }
}