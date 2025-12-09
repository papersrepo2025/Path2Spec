
public class NotCommonFactor {
    //@ requires factor != 0;
    //@ ensures \result == (a % factor != 0 || b % factor != 0);
    //@ also
    //@ requires factor != 0;
    //@ requires a % factor == 0;
    //@ requires b % factor == 0;
    //@ ensures \result == false;
    public boolean notCommonFactor(int a, int b, int factor) {
        return a % factor != 0 || b % factor != 0;
    }
}
