
public class IsCommonFactor {
    //@ requires factor > 0;
    //@ requires a % factor == 0 && b % factor == 0;
    //@ ensures \result == true;
    //@ also
    //@ requires factor > 0 && (a % factor != 0 || b % factor != 0);
    //@ ensures \result == false;
    public boolean isCommonFactor (int a, int b, int factor) {
        return a % factor == 0 && b % factor == 0;
    }
}
