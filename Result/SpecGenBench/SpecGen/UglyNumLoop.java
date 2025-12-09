class UglyNumLoop {
    //@ ensures \old(n) <= 0 ==> \result == false;
 
    //@ assignable \nothing;
    /*@ pure @*/ public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        //@ maintaining n > 0 && \old(n) % n == 0;
        //@ decreases n;
        while (n % 2 == 0) {
            n /= 2;
        }
        //@ maintaining n > 0 && \old(n) % n == 0 && n % 2 != 0;
        //@ decreases n;
        while (n % 3 == 0) {
            n /= 3;
        }
        //@ maintaining n > 0 && \old(n) % n == 0 && n % 2 != 0 && n % 3 != 0;
        //@ decreases n;
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
