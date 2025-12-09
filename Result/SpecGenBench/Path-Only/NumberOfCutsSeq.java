
class NumberOfCutsSeq {
    //@ requires n % 2 == 0 && n != 1;
    //@ ensures \result == n / 2;
    //@ also
    //@ requires n == 1;
    //@ ensures \result == 0;
    public int numberOfCuts(int n) {
        return ((n == 1) ? 0 : ((n % 2 == 0) ? (n / 2) : n));
    }
}
