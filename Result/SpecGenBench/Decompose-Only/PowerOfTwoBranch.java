
class PowerOfTwoBranch {
    /*@
      @    requires n <= 0;
      @    ensures \result == false;
      @ also
      @    requires n > 0 && ((n & (n - 1)) != 0);
      @    ensures \result == false;
      @ also
      @    requires n > 0 && ((n & (n - 1)) == 0);
      @    ensures \result == true;
      @*/
    public /*@ pure @*/ boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return true;
    }
}