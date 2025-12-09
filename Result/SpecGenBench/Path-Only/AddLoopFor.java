
public class AddLoopFor {
    //@ requires x > 0 && Integer.MIN_VALUE <= y + x && y + x <= Integer.MAX_VALUE;
    //@ ensures \result == y + x;
    public static int addLoop(int x, int y) {
        int sum = y;
        if (x > 0) {
            int n = x;
            //@ loop_invariant sum == y + (x - n) && 0 <= n;
            //@ decreases n;
            for(; n > 0; n = n - 1) {
                sum = sum + 1;
            }
        } else {
            int n = -x;
            //@ loop_invariant sum == y - (x + n) && 0 <= n;
            //@ decreases n;
            for(; n > 0; n = n - 1) {
                sum = sum - 1;
            }
        }
        return sum;
    }
}
