class SmallestEvenMul {
    //@ ensures (n % 2 == 0) ==> (\result == n);
    //@ ensures (n % 2 != 0) ==> (\result == 2 * n);
    //@ ensures \result % 2 == 0;
    //@ ensures (n != 0) ==> (\result % n == 0);
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }
}