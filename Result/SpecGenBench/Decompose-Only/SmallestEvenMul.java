
class SmallestEvenMul {
    //@ requires n % 2 == 0;
    //@ ensures \result == n;
    //@ also
    //@ requires n % 2 != 0;
    //@ ensures \result == 2 * n;
    public /*@ pure @*/ int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }
}