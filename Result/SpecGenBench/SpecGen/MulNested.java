public class MulNested {
    //@ requires x != Integer.MIN_VALUE && y != Integer.MIN_VALUE;
    //@ requires ((\bigint)x) * ((\bigint)y) >= Integer.MIN_VALUE && ((\bigint)x) * ((\bigint)y) <= Integer.MAX_VALUE;
    //@ assignable \nothing;
    //@ ensures \result == x * y;
    public static int mulNested(int x, int y) {
        int res = 0, m = x, n = y, sign = 1;
        if(x < 0){
            m = -x;
            sign = -sign;
        }
        if(y < 0){
            n = -y;
            sign = -sign;
        }
        //@ maintaining 0 <= i && i <= m;
        //@ maintaining 0 <= m && 0 <= n;
        //@ maintaining res == i * n;
        //@ decreases m - i;
        for(int i = 0; i < m; i++) {
            //@ maintaining 0 <= j && j <= n;
            //@ maintaining 0 <= i && i < m;
            //@ maintaining res == i * n + j;
            //@ decreases n - j;
            for(int j = 0; j < n; j++) {
                res = res + 1;
            }
        }
        return sign * res;
    }
}