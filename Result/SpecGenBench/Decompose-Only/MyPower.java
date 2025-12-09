class MyPower {
    //@ requires n > 0;
    //@ requires Integer.MIN_VALUE <= (long)x * (long)x && (long)x * (long)x <= Integer.MAX_VALUE;
    //@ requires Integer.MIN_VALUE <= ((long)x * (long)x) * (long)x && ((long)x * (long)x) * (long)x <= Integer.MAX_VALUE;
    //@ ensures \result == x * x * x;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires n > 0 && -1290 <= x && x <= 1290;
    //@ ensures \result == x * x * x;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires n > 5 && -1 <= x && x <= 1;
    //@ ensures x == 0 ==> \result == 0;
    //@ ensures x == 1 ==> \result == 1;
    //@ ensures x == -1 ==> (\result == -1 || \result == 1);
    //@ ensures -1 <= \result && \result <= 1;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    public static int power(int x, int n) {
        int res = 1;
        int i = 0;
        res = res * x;
        i++;
        res = res * x;
        i++;
        res = res * x;
        i++;
        return res;
    }
}