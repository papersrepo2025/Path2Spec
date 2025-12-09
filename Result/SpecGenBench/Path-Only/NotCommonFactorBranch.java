
public class NotCommonFactorBranch {
    //@ requires factor != 0 && a % factor != 0;
    //@ ensures \result == true;
    //@ also
    //@ requires factor != 0 && a % factor == 0 && b % factor != 0;
    //@ ensures \result == true;
    //@ also
    //@ requires factor != 0; // Division by zero check;
    //@ requires a % factor == 0 && b % factor == 0; // factor is a common factor of a and b;
    //@ ensures \result == false; // Expected output for this path;
    public boolean notCommonFactor (int a, int b, int factor) {
        if(a % factor != 0) {
            return true;
        }
        if(b % factor != 0) {
            return true;
        }
        return false;
    }
}
