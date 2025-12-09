
public class Absolute {

    //@ assignable \nothing;
    //@ requires 0 <= num;
    //@ ensures \result == num;
    //@ also
    //@ requires num < 0;
    //@ ensures \result == -num;
    /*@ pure @*/ public int Absolute(int num) {
        if (0 <= num)
            return num;
        else
            return -num;
    }

    //@ assignable \nothing;
    //@ requires 0 <= num;
    //@ ensures \result == num;
    //@ also
    //@ requires num < 0;
    //@ ensures \result == -num;
    /*@ pure @*/ public long Absolute(long num) {
        if (0 <= num)
            return num;
        else
            return -num;	
    }
}