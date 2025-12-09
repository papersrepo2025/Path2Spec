

class SmallestEvenMulBranch {
    /*@ 
      @ requires n % 2 == 0;
      @ ensures \result == n;
      @ also
      @ requires n % 2 != 0;
      @ ensures \result == (int)(2 * n);
      @*/
    public /*@ pure @*/ int smallestEvenMultiple(int n) {
        if(n % 2 == 0) {
            return n;
        }
        //@ assert n % 2 != 0;
        return 2 * n;
    }
}