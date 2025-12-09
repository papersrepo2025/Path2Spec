
public class IntSquare {

    //@ requires Integer.MIN_VALUE <= x && x <= Integer.MAX_VALUE;
    //@ ensures \result == x * x;
    public static int squareOf(int x) {
        if(x < 0)
            x = -x;
        int res = 0;
        //@ maintaining res == i * x && 0 <= i && i <= x;
        //@ decreases x - i;
        for(int i = 0; i < x; i++) {
            //@ maintaining res == i * x + j;
            //@ maintaining 0 <= j && j <= x;
            //@ decreases x - j;
            for(int j = 0; j < x; j++) {
                res = res + 1;
            }
        }
        return res;
    }

}
