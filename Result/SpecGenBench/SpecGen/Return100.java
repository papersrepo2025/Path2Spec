public class Return100 {
    /*@ public normal_behavior
      @   ensures \result == 100;
      @   assignable \nothing;
      @*/
    public static int return100 () {
        int res = 0;
        //@ maintaining 0 <= i && i <= 100;
        //@ maintaining res == i;
        for(int i = 0; i < 100; i++) {
            res = res + 1;
        }
        //@ assert res == 100;
        return res;
    }
}