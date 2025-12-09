class PowerOfFourLoop {
 
    //@ ensures \old(n) <= 0 ==> (!\result && n == \old(n));
 
    public boolean isPowerOfFour(int n) {
        if(n <= 0) {
            return false;
        }
        //@ maintaining n > 0;
        //@ decreases n;
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}
