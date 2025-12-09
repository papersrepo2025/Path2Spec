
class UglyNum {
    //@ requires n <= 0;
//@ ensures \result == false;
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int i = 0; i < factors.length; i++) {
            int factor = factors[i];
            //@ maintaining n != 0;
            //@ decreases n;
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
