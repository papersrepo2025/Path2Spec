public class AddLoopFor {
    //@ public normal_behavior
    //@   requires x > 0 && y <= Integer.MAX_VALUE - x;
    //@   ensures \result == \old(y) + \old(x);
    //@ also
    //@ public normal_behavior
    //@   requires x <= 0 && x > Integer.MIN_VALUE && y >= Integer.MIN_VALUE - x;
    //@   ensures \result == \old(y) + \old(x);
    public static int addLoop(int x, int y) {
        int sum = y;
        if (x > 0) {
            //@ maintaining 0 <= n && n <= x;
            //@ maintaining sum == y + (x - n);
            //@ decreases n;
            for(int n = x; n > 0; n = n - 1) {
                sum = sum + 1;
            }
        } else {
            //@ maintaining 0 <= n && n <= -x;
            //@ maintaining sum == y + x + n;
            //@ decreases n;
            for(int n = -x; n > 0; n = n - 1) {
                sum = sum - 1;
            }
        }
        return sum;
    }
}