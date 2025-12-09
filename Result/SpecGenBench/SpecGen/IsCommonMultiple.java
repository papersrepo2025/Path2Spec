public class IsCommonMultiple {
    //@ requires a != 0 && b != 0;
    //@ ensures \result <==> (m % a == 0 && m % b == 0);
    /*@ spec_public @*/
    public boolean isCommonMultiple (int a, int b, int m) {
        return m % a == 0 && m % b == 0;
    }
}