public class Addition {
    //@ requires n <= 0;
    //@ requires (\bigint)Integer.MIN_VALUE <= (\bigint)m + (\bigint)n && (\bigint)m + (\bigint)n <= (\bigint)Integer.MAX_VALUE;
    //@ ensures \result == m + n;
    //@ also
    //@ requires n == 0;
    //@ ensures \result == m;
    //@ also
    //@ requires n > 0 && Integer.MIN_VALUE <= m + n && m + n <= Integer.MAX_VALUE;
    //@ ensures \result == m + n;
    public static int addition(int m, int n) {
        if (n == 0) {
            return m;
        }
        if (n > 0) {
            return addition(m + 1, n - 1);
        } else {
            return addition(m - 1, n + 1);
        }
    }
}
