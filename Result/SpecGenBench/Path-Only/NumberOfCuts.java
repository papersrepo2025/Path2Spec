
class NumberOfCuts {
    //@ requires n == 1;
    //@ ensures \result == 0;
    //@ also
    //@ requires n > 1 && n % 2 == 0;
    //@ ensures \result == n / 2;
    //@ also
    //@ requires n > 1 && n % 2 != 0;
    //@ ensures \result == n;
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return n / 2;
        }
        return n;
    }
}
