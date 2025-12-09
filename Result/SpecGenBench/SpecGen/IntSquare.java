public class IntSquare {

    //@ requires -46340 <= x && x <= 46340;
    //@ ensures \result == \old(x) * \old(x);
    //@ ensures \result >= 0;
    public static int squareOf(int x) {
        if(x < 0)
            x = -x;
        int res = 0;
        //@ maintaining x >= 0;
        //@ maintaining 0 <= i && i <= x;
        //@ maintaining 0 <= res && res == i * x;
        //@ decreases x - i;
        for(int i = 0; i < x; i++) {
            //@ maintaining x >= 0;
            //@ maintaining 0 <= j && j <= x;
            //@ maintaining res == i * x + j;
            //@ decreases x - j;
            for(int j = 0; j < x; j++) {
                res = res + 1;
            }
        }
        return res;
    }

}