public class AddLoop {
    //@ ensures \result == x + y;
    public static int AddLoop(int x, int y) {
        int sum = x;
        if (y > 0) {
            int n = y;
            //@ maintaining 0 <= n && n <= y && sum == x + (y - n) && y > 0;
            //@ decreases n;
            while (n > 0) {
                sum = sum + 1;
                n = n - 1;
            }
        } else {
            int n = -y;
            //@ maintaining 0 <= n && n <= -y && sum == x + y + n && y <= 0;
            //@ decreases n;
            while (n > 0) {
                sum = sum - 1;
                n = n - 1;
            }
        }
        return sum;
    }
}