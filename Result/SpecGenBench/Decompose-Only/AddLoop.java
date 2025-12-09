
public class AddLoop {
    //@ requires y > 0;
    //@ ensures \result == x + y;
    //@ also
    //@ requires y == 0;
    //@ ensures \result == x;
    //@ also
    //@ requires y < 0;
    //@ ensures \result == x + y;
    public static int AddLoop(int x, int y) {
        int sum = x;
        if (y > 0) {
            //@ ghost int y0 = y;
            //@ assert y > 0;
            int n = y;
            //@ maintaining 0 <= n && n <= y0;
            //@ maintaining sum == x + (y0 - n);
            //@ decreases n;
            while (n > 0) {
                //@ assert n > 0;
                sum = sum + 1;
                //@ assert sum == x + (y0 - (n - 1));
                n = n - 1;
            }
        } else {
            //@ ghost int y0 = y;
            //@ assert y <= 0;
            int n = -y;
            //@ maintaining 0 <= n && n <= -y0;
            //@ maintaining sum == x + y0 + n;
            //@ decreases n;
            while (n > 0) {
                //@ assert n > 0;
                sum = sum - 1;
                //@ assert sum == x + y0 + (n - 1);
                n = n - 1;
            }
        }
        //@ assert (y > 0 ==> sum == x + y) && (y == 0 ==> sum == x) && (y < 0 ==> sum == x + y);
        return sum;
    }
}