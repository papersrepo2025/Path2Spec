
class SmallestEvenMulBranch {
    //@ requires n % 2 == 0;
    //@ ensures \result == n;
    //@ also
    //@ requires n % 2 != 0;
    //@ ensures \result == 2 * n;
    public int smallestEvenMultiple(int n) {
        if(n % 2 == 0) {
            return n;
        }
        return 2 * n;
    }
}
