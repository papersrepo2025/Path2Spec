
public class Return100Nested {
    //@ ensures \result == 100;
    //@ assignable \nothing;
    //@ signals (Exception e) false;
    public static int return100 () {
        int res = 0;
        //@ loop_invariant 0 <= i && i <= 10;
        //@ loop_invariant 0 <= res && res <= 100;
        //@ loop_invariant res == 10*i;
        //@ decreases 10 - i;
        for(int i = 0; i < 10; i++) {
            //@ assume i != 0;
            //@ loop_invariant 0 <= j && j <= 10;
            //@ loop_invariant res == 10*i + j;
            //@ decreases 10 - j;
            for(int j = 0; j < 10; j++) {
                //@ assume i != 0;
                //@ assert res == 10*i + j;
                res = res + 1;
            }
        }
        //@ assert res == 100;
        return res;
    }
}