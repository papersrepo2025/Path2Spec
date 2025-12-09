
public class IntSquare {

    //@ requires x < 0 && -x <= 46340;
    //@ ensures \result == \old(x) * \old(x);
    //@ also
    //@ requires x >= 0 && x <= 46340;
    //@ ensures \result == \old(x) * \old(x);
    //@ also
    //@ requires x < -46340 || x > 46340;
    //@ ensures true;
    public static int squareOf(int x) {
        if(x < 0)
            x = -x;
        int res = 0;
        //@ maintaining 0 <= i && i <= x;
        //@ maintaining res == i * x;
        //@ decreases x - i;
        for(int i = 0; i < x; i++) {
            //@ assume i != 0;
            //@ maintaining 0 <= j && j <= x;
            //@ maintaining res == i * x + j;
            //@ decreases x - j;
            for(int j = 0; j < x; j++) {
                //@ assume j != 0;
                res = res + 1;
            }
        }
        return res;
    }

}