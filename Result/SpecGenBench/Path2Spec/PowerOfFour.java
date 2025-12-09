
class PowerOfFour {
    //@ requires n <= 0;
    //@ ensures \result == false;
    //@ also
    //@ requires n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    //@ ensures \result == true;
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
