
public class NotCommonFactorBranch {
    /*@ public normal_behavior
      @   requires factor != 0;
      @   assignable \nothing;
      @   ensures \result <==> (a % factor != 0) || (b % factor != 0);
      @ also
      @ public exceptional_behavior
      @   requires factor == 0;
      @   assignable \nothing;
      @   signals (ArithmeticException e) true;
      @*/
    public boolean notCommonFactor (int a, int b, int factor) {
        //@ assume factor != 0;
        if(a % factor != 0) {
            return true;
        }
        //@ assume factor != 0;
        if(b % factor != 0) {
            return true;
        }
        return false;
    }
}