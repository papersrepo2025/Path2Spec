
public class MulLoop {
    //@ requires Integer.MIN_VALUE <= a * b && a * b <= Integer.MAX_VALUE;
    //@ ensures \result == a * b;
    //@ also
    //@ requires Integer.MIN_VALUE <= a * b && a * b <= Integer.MAX_VALUE && b >= 0;
    //@ ensures \result == a * b;
    //@ also
    //@ requires Integer.MIN_VALUE <= a * b && a * b <= Integer.MAX_VALUE && b != Integer.MIN_VALUE;
    //@ ensures \result == a * b;
    public static int mulLoop(int a, int b) {
        int res = 0;
        if (b >= 0) {
            //@ maintaining res == a * i && 0 <= i && i <= b;
            //@ decreases b - i;
            for(int i = 0; i < b; i++) {
                res = res + a;
            }
        } else {
            //@ maintaining res == -(a * i) && 0 <= i && i <= -b;
            //@ decreases -b - i;
            for(int i = 0; i < -b; i++) {
                res = res - a;
            }
        }
        return res;
    }

}
