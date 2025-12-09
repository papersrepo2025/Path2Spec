
public class NotCommonMultiple {
    //@ requires a != 0 && b != 0; // Ensure that neither 'a' nor 'b' is zero to avoid division by zero issues.;
    //@ ensures \result == (m % a != 0 || m % b != 0); // Ensure the result reflects the logic of not being a common multiple.;
    //@ also
    //@ requires a != 0 && b != 0;  // Ensure a and b are not zero to avoid division by zero;
    //@ ensures (m % a != 0 || m % b != 0) <==> \result;  // Ensure the result corresponds to the logic condition;
    public boolean NotCommonMultiple (int a, int b, int m) {
        return m % a != 0 || m % b != 0;
    }
}
