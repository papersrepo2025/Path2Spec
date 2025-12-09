public class NotCommonFactorBranch {
    /*@ 
      @ public normal_behavior
      @   requires factor != 0;
      @   ensures \result == ((a % factor != 0) || (b % factor != 0));
      @   assignable \nothing;
      @*/
    /*@ spec_public @*/ public boolean notCommonFactor (int a, int b, int factor) {
        if(a % factor != 0) {
            return true;
        }
        if(b % factor != 0) {
            return true;
        }
        return false;
    }
}