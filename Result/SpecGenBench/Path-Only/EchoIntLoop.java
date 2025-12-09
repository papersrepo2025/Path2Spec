
public class EchoIntLoop {
    //@ requires x > 0;
    //@ ensures \result == x;
    public static int echo(int x) {
        int res = 0;
        //@ maintaining 0 <= i && i <= x && res == i;
//@ decreases x - i;
        for(int i = 0; i < x; i++) {
            res = res + 1;
        }
        return res;
    }
}
