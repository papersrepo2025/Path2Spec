
public class AddTwoLoop {
    //@ requires a >= 0 && b >= 0 && Integer.MIN_VALUE <= a + b && a + b <= Integer.MAX_VALUE;
    //@ ensures \result == a + b;
    //@ also
    //@ requires a < 0 && b >= 0 && Integer.MIN_VALUE <= a + b && a + b <= Integer.MAX_VALUE;
    //@ ensures \result == a + b;
    //@ also
    //@ requires a >= 0 && b < 0 && Integer.MIN_VALUE <= a + b && a + b <= Integer.MAX_VALUE && b != Integer.MIN_VALUE;
    //@ ensures \result == a + b;
    //@ also
    //@ requires a < 0 && b < 0;
    //@ requires Integer.MIN_VALUE - a <= b && b <= Integer.MAX_VALUE + a;
    //@ ensures \result == a + b;
    public static int add(int a, int b) {
        int m = ((a < 0) ? -a : a);
        int n = ((b < 0) ? -b : b);
        int res = 0;
        
        //@ maintaining 0 <= i && i <= m;
        //@ maintaining res == i * ((a < 0) ? -1 : 1);
        //@ decreases m - i;
        for(int i = 0; i < m; i++){
            res = res + ((a < 0) ? -1 : 1);
        }
        
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining res == m * ((a < 0) ? -1 : 1) + i * ((b < 0) ? -1 : 1);
        //@ decreases n - i;
        for(int i = 0; i < n; i++){
            res = res + ((b < 0) ? -1 : 1);
        }
        
        return res;
    }
}
