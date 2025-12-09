
class PowerOfFour {
    /*@ public normal_behavior
      @   requires n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
      @   ensures \result == true;
      @   assignable \nothing;
      @ also
      @ public normal_behavior
      @   requires !(n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0);
      @   ensures \result == false;
      @   assignable \nothing;
      @*/
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}