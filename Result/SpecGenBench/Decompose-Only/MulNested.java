
public class MulNested {

    /*@ public normal_behavior
      @   assignable \nothing;
      @   ensures \result == \old(x) * \old(y);
      @*/
    public static int mulNested(int x, int y) {
        int res = 0, m = x, n = y, sign = 1;
        if(x < 0){
            //@ assert x < 0;
            m = -x;
            //@ assert sign == 1 || sign == -1;
            sign = -sign;
        }
        if(y < 0){
            //@ assert y < 0;
            n = -y;
            //@ assert sign == 1 || sign == -1;
            sign = -sign;
        }
        //@ maintaining 0 <= i && i <= m;
        //@ maintaining res == i * n;
        //@ decreases m - i;
        for(int i = 0; i < m; i++) {
            //@ assume i != 0;
            //@ maintaining 0 <= j && j <= n;
            //@ maintaining res == i * n + j;
            //@ decreases n - j;
            for(int j = 0; j < n; j++) {
                //@ assume i != 0;
                //@ assert res == i * n + j;
                res = res + 1;
            }
        }
        return sign * res;
    }
}