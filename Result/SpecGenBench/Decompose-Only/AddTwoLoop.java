
public class AddTwoLoop {
    /*@ 
      @ requires a != Integer.MIN_VALUE && b != Integer.MIN_VALUE;
      @ requires Integer.MIN_VALUE - a <= b && b <= Integer.MAX_VALUE - a;
      @ ensures \result == a + b;
      @*/
    public static int add(int a, int b) {
        int m = ((a < 0) ? -a : a);
        int n = ((b < 0) ? -b : b);
        int res = 0;
        //@ maintaining 0 <= i && i <= m;
        //@ maintaining res == i * (((a < 0) ? -1 : 1));
        //@ decreases m - i;
        for(int i = 0; i < m; i++){
            //@ assume i != 0;
            res = res + ((a < 0) ? -1 : 1);
        }
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining res == ((a < 0) ? -m : m) + i * (((b < 0) ? -1 : 1));
        //@ decreases n - i;
        for(int i = 0; i < n; i++){
            //@ assume i != 0;
            res = res + ((b < 0) ? -1 : 1);
        }
        return res;
    }
}