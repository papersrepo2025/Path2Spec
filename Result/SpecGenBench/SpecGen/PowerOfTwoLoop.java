class PowerOfTwoLoop {
    //@ ensures \result <==> (\old(n) > 0 && ((\old(n) & (\old(n) - 1)) == 0));
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        //@ ghost int n0 = n;
        //@ maintaining n > 0;
        //@ maintaining n0 % n == 0;
        //@ maintaining (((n0 / n) & ((n0 / n) - 1)) == 0);
        //@ decreases n;
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}