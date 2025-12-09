public class EchoIntLoop {
    //@ requires Integer.MIN_VALUE <= x && x <= Integer.MAX_VALUE;
    //@ also
    //@ ensures x >= 6 ==> \result == x;
    //@ ensures x < 6 ==> \result == 6;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires x > 6;
    //@ ensures \result == x;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires 6 < x;
    //@ ensures \result == x;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires x > 5;
    //@ ensures \result == x;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    public static int echo(int x) {
        int res = 0;
        int i = 0;
        //@ assert res == 0;
        //@ assert i == 0;
        // First iteration
        res = res + 1;
        i++;
        // Second iteration
        res = res + 1;
        i++;
        // Third iteration
        res = res + 1;
        i++;
        // Remaining iterations
        // Execute loop body three times since (i < x) remains true
        res = res + 1;
        i++;
        res = res + 1;
        i++;
        res = res + 1;
        i++;
        //@ maintaining res == i;
        //@ maintaining 6 <= i;
        //@ maintaining x >= 6 ==> i <= x;
        //@ maintaining x < 6 ==> i == 6 && res == 6;
        //@ maintaining x >= 6 ==> res <= x;
        //@ maintaining res <= Integer.MAX_VALUE;
        //@ maintaining Integer.MIN_VALUE <= res && res <= Integer.MAX_VALUE;
        //@ decreases x <= i ? 0 : x - i;
        for (; i < x; i++) {
            res = res + 1;
        }
        return res;
    }
}