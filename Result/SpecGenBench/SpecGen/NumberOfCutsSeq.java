class NumberOfCutsSeq {
    //@ assignable \nothing;
    //@ ensures \result == ((n == 1) ? 0 : ((n % 2 == 0) ? (n / 2) : n));
    public /*@ pure @*/ int numberOfCuts(int n) {
        return ((n == 1) ? 0 : ((n % 2 == 0) ? (n / 2) : n));
    }
}