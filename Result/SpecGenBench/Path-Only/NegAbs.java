
public class NegAbs {
    //@ requires num < 0;
    //@ ensures \result == num;
    //@ also
    //@ requires num >= 0;
    //@ ensures \result == -num;
    public int negAbs(int num) {
        if (num < 0)
            return num;
        else
            return -num;
    }
}
