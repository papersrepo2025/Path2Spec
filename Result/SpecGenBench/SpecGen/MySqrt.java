class MySqrt {
    //@ requires x >= 0;
 
    //@ ensures (\exists int y; y > 0 && ((long)y)*y < x; y <= \result);  
    /*@ pure spec_public @*/ public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        //@ maintaining 0 <= l && l <= r + 1 && r <= x && -1 <= ans;
        //@ maintaining ans < l && (ans == -1 || ((long)ans)*ans <= x);
        //@ maintaining (\forall int i; 0 <= i && i < l; ((long)i)*i <= x);
        //@ maintaining (\forall int i; r < i && i <= x; ((long)i)*i > x);
        //@ decreases r - l + 1;
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
