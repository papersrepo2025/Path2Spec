class PowerOfTwoBranch {
    //@ ensures \result <==> (n > 0 && (n & (n - 1)) == 0);
    //@ ensures \result ==> (\exists int k; 0 <= k && k < 31; n == (1 << k));
    public /*@ pure @*/ boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return true;
    }
}