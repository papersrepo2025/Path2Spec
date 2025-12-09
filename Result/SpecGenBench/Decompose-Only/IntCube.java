public class IntCube {

        //@ public normal_behavior
        //@   requires x >= 0;
        //@ assignable \nothing;
        //@ ensures \result == \old(x) * \old(x) * \old(x);
    public static int cubeOf(int x) {
            //@ assert x >= 0;
        boolean neg = false;
        if(x < 0) {
            neg = true;
            x = -x;
        }
            //@ assert !neg;
        int res = 0;
            //@ assert res == 0;
            //@ maintaining 0 <= i && i <= x;
            //@ maintaining res == i * x * x;
            //@ decreases x - i;
        for(int i = 0; i < x; i++) {
            //@ assume i != 0;
                //@ assume i != 0;
                //@ maintaining 0 <= j && j <= x;
                //@ maintaining res == i * x * x + j * x;
                //@ decreases x - j;
            for(int j = 0; j < x; j++) {
                //@ assume i != 0;
                //@ assume j != 0;
                    //@ assume i != 0;
                    //@ assume j != 0;
                    //@ maintaining 0 <= k && k <= x;
                    //@ maintaining res == i * x * x + j * x + k;
                    //@ decreases x - k;
                for(int k = 0; k < x; k++) {
                    //@ assume i != 0;
                    //@ assume k != 0;
                        //@ assert res >= 0;
                    res = res + 1;
                }
            }
        }
        return (neg ? -res : res);
    }

}