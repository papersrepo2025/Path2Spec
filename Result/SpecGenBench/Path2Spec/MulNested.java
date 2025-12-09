
public class MulNested {
    //@ requires Integer.MIN_VALUE <= x * y && x * y <= Integer.MAX_VALUE;
    //@ ensures (x == 0 || y == 0) ==> \result == 0;
    //@ ensures x != 0 && y != 0 ==> \result == x * y;
    //@ also
    //@ requires x != 0 && y != 0;
    //@ requires Integer.MIN_VALUE <= x * y && x * y <= Integer.MAX_VALUE;
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
        //@ loop_invariant 0 <= i && i <= m;
        //@ maintaining res == i * n;
        //@ decreases m - i;
        for(int i = 0; i < m; i++) {
            //@ loop_invariant 0 <= j && j <= n;
            //@ maintaining res == i * n + j;
            //@ decreases n - j;
            for(int j = 0; j < n; j++) {
                res = res + 1;
            }
        }
        return sign * res;
    }
}
