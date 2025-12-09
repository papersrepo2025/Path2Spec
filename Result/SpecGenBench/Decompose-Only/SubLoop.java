

public class SubLoop {
    //@ requires y > 0 && Integer.MIN_VALUE <= x - y && x - y <= Integer.MAX_VALUE;
    //@ ensures \result == x - y;
    //@ also
    //@ requires y <= 0 && y != Integer.MIN_VALUE && Integer.MIN_VALUE <= x - y && x - y <= Integer.MAX_VALUE;
    //@ ensures \result == x - y;
    public static int subLoop(int x, int y) {
        int sum = x;
        if (y > 0) {
            int n = y;
            //@ maintaining sum == x - y + n && 0 <= n;
            //@ decreases n;
            while (n > 0) {
                //@ assert sum > Integer.MIN_VALUE;
                sum = sum - 1;
                //@ assert n > Integer.MIN_VALUE;
                n = n - 1;
            }
        } else {
            //@ assert y != Integer.MIN_VALUE;
            int n = -y;
            //@ maintaining sum == x - y - n && 0 <= n;
            //@ decreases n;
            while (n > 0) {
                //@ assert sum < Integer.MAX_VALUE;
                sum = sum + 1;
                //@ assert n > Integer.MIN_VALUE;
                n = n - 1;
            }
        }
        return sum;
    }
}