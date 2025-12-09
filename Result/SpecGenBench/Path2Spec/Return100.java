
public class Return100 {
    //@ ensures \result == 100;
    public static int return100 () {
        int res = 0;
        //@ loop_invariant 0 <= i && i <= 100 && res == i;
//@ decreases 100 - i;
        for(int i = 0; i < 100; i++) {
            res = res + 1;
        }
        return res;
    }
}
