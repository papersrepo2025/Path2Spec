
class PowerOfTwo {
    //@ requires n <= 0;
//@ ensures \result == false;
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
