public class AddLoopFor {
    /*@
      @ requires x != Integer.MIN_VALUE;
      @ requires (x > 0) ==> ((long)y + (long)x <= Integer.MAX_VALUE);
      @ requires (x < 0) ==> ((long)y + (long)x >= Integer.MIN_VALUE);
      @ ensures (long)\result == (long)y + (long)x;
      @*/
    public static int addLoop(int x, int y) {
        int sum = y;
        if (x > 0) {
            /*@
              @ maintaining 0 <= n && (long)n <= (long)x;
              @ maintaining (long)sum == (long)y + ((long)x - (long)n);
              @ maintaining (x >= 0) ==> ((long)y <= (long)sum && (long)sum <= (long)y + (long)x);
              @*/
            for(int n = x; n > 0; n = n - 1) {
                sum = sum + 1;
            }
        } else {
            /*@
              @ maintaining 0 <= n && (long)n <= - (long)x;
              @ maintaining (long)sum == (long)y + ((long)n + (long)x);
              @ maintaining (x <= 0) ==> ((long)y + (long)x <= (long)sum && (long)sum <= (long)y);
              @*/
            for(int n = -x; n > 0; n = n - 1) {
                sum = sum - 1;
            }
        }
        return sum;
    }
}