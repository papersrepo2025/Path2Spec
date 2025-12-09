class UglyNum {
    //@ requires Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;
    //@ requires n <= 0;
    //@ requires 2 != 0 && 3 != 0 && 5 != 0;
    //@ ensures \result == false;
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        //@ maintaining factors != null;
        //@ maintaining 0 <= i && i <= factors.length;
        //@ decreases factors.length - i;
        for (int i = 0; i < factors.length; i++) {
            int factor = factors[i];
            //@ maintaining factors != null;
            //@ maintaining factor == 2 || factor == 3 || factor == 5;
            //@ maintaining factor != 0;
            //@ maintaining Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;
            //@ decreases (n == Integer.MIN_VALUE ? Integer.MAX_VALUE : (n < 0 ? -n : n));
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}