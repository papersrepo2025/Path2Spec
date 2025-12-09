class UglyNum {
    /*@ 
 
      @*/
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        //@ maintaining 0 <= i && i <= factors.length;
        //@ maintaining n > 0;
 
        //@ decreases factors.length - i;
        for (int i = 0; i < factors.length; i++) {
            int factor = factors[i];
            //@ maintaining n > 0;
            //@ maintaining factor == factors[i];
            //@ decreases n;
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
