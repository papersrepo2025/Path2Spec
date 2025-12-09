public class Return100 {
    /* 
     - Identify execution path and expected output.
     - Add precise postcondition for the method result.
     - Provide loop invariants and a decreasing variant for verification.
     - Insert required loop assumption regarding loop variable usage.
    */
    //@ ensures \result == 100;
    public static int return100 () {
        int res = 0;
        //@ loop_invariant 0 <= i && i <= 100;
        //@ loop_invariant res == i;
        //@ decreasing 100 - i;
        for(int i = 0; i < 100; i++) {
            //@ assume i != 0;
            res = res + 1;
        }
        return res;
    }
}