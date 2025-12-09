
class MySqrt {
    /*@ public normal_behavior
      @   requires x < 0;
      @   assignable \nothing;
      @   ensures \result == -1;
      @ also
      @ public normal_behavior
      @   requires 0 <= x;
      @   assignable \nothing;
      @   ensures 0 <= \result
      @        && ((long)\result) * (long)\result <= (long)x
      @        && (((long)\result + 1L) * ((long)\result + 1L) > (long)x);
      @*/
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        //@ maintaining l == ans + 1;
        //@ maintaining 0 <= l;
        //@ maintaining x < 0 ==> (l == 0 && r == x && ans == -1);
        //@ maintaining 0 <= x ==> (-1 <= ans && ans <= r && ((long)l) <= ((long)r) + 1L);
        //@ maintaining 0 <= x ==> (((long)r + 1L) * ((long)r + 1L) > (long)x);
        //@ maintaining 0 <= x ==> (ans == -1 || ((long)ans) * (long)ans <= (long)x);
        //@ decreases ((long)r) - ((long)l) + 1L;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}