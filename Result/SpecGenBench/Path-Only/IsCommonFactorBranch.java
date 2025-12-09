
public class IsCommonFactorBranch {
    //@ requires factor > 0 && a % factor != 0;
    //@ ensures \result == false;
    //@ also
    //@ requires factor > 0 && a % factor == 0 && b % factor != 0;
    //@ ensures \result == false;
    //@ also
    //@ requires factor > 0 && a % factor == 0 && b % factor == 0;
    //@ ensures \result == true;
    public boolean isCommonFactor (int a, int b, int factor) {
        if (a % factor != 0) {
            return false;
        }
        if (b % factor != 0) {
            return false;
        }
        return true;
    }
}
