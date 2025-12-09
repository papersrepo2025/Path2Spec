class PrimeCheck {
    //@ ensures this != null && Integer.MIN_VALUE <= a && a <= Integer.MAX_VALUE && a/2 < 2 ==> \result == true;
    //@ also
    //@ ensures true ==> \result;
    public boolean isPrime(int a) {
        int i = 2;
        int mid = a/2;

        return true;
    }
}