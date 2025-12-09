
public class Absolute {
    //@ requires 0 <= num;
    //@ ensures \result == num;
    //@ also
    //@ requires num < 0;
    //@ ensures \result == -num;
    public int Absolute(int num) {
        if (0 <= num)
            return num;
        else
            return -num;
    }
    //@ requires 0 <= num;
    //@ ensures \result == num;
    //@ also
    //@ requires num < 0;
    //@ ensures \result == -num;
    public long Absolute(long num) {
        if (0 <= num)
            return num;
        else
            return -num;    
    }
}
