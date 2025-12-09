class PowerOfTwoLoop {
    //@ requires Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;
    //@ also
    //@ requires n <= 0;
    //@ ensures \result == false;
    //@ also
    //@ requires n > 0;
    //@ ensures true;
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        //@ maintaining n > 0;
        //@ decreases n;
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}