
class PowerOfFourBranch {
    /*@ public normal_behavior
      @ requires n <= 0;
      @ ensures \result == false;
      @ assignable \nothing;
      @ also
      @ public normal_behavior
      @ requires n > 0 && (n & (n - 1)) != 0;
      @ ensures \result == false;
      @ assignable \nothing;
      @ also
      @ public normal_behavior
      @ requires n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) != 0;
      @ ensures \result == false;
      @ assignable \nothing;
      @ also
      @ public normal_behavior
      @ requires n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
      @ ensures \result == true;
      @ assignable \nothing;
      @*/
    public /*@ pure @*/ boolean isPowerOfFour(int n) {
        if(n <= 0) {
            return false;
        }
        if((n & (n - 1)) != 0) {
            return false;
        }
        if((n & 0xaaaaaaaa) != 0) {
            return false;
        }
        return true;
    }
}