public class AddTwoLoop {
    //@ requires a != Integer.MIN_VALUE && b != Integer.MIN_VALUE;
    //@ requires (b >= 0 ==> a <= Integer.MAX_VALUE - b) && (b < 0 ==> a >= Integer.MIN_VALUE - b);
    //@ ensures \result == a + b;
    public static int add(int a, int b) {
        int m = ((a < 0) ? -a : a);
        int n = ((b < 0) ? -b : b);
        int res = 0;
        //@ maintaining 0 <= i && i <= m;
        //@ maintaining m == ((a < 0) ? -a : a);
        //@ maintaining res == i * ((a < 0) ? -1 : 1);
        //@ decreases m - i;
        for(int i = 0; i < m; i++){
            res = res + ((a < 0) ? -1 : 1);
        }
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining n == ((b < 0) ? -b : b);
        //@ maintaining res == a + i * ((b < 0) ? -1 : 1);
        //@ decreases n - i;
        for(int i = 0; i < n; i++){
            res = res + ((b < 0) ? -1 : 1);
        }
        return res;
    }
}