
class PowerOfTwo {
    /*@ assignable \nothing;
      @ requires n <= 0;
      @ ensures !\result;
      @ also
      @ requires n > 0 && (n & (n - 1)) == 0;
      @ ensures \result;
      @ also
      @ requires n > 0 && (n & (n - 1)) != 0;
      @ ensures !\result;
      @*/
    /*@ pure @*/ public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}