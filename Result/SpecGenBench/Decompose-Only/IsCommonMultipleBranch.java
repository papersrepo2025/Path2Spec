public class IsCommonMultipleBranch {
    /*@ 
      @ requires a != 0;
      @ requires b != 0;
      @ {|
      @   requires m % a != 0;
      @   ensures \result == false;
      @ also
      @   requires m % a == 0 && m % b != 0;
      @   ensures \result == false;
      @ also
      @   requires m % a == 0 && m % b == 0;
      @   ensures \result == true;
      @ |}
      @*/
    public boolean isCommonMultiple (int a, int b, int m) {
        if(m % a != 0) {
            return false;
        }
        if(m % b != 0) {
            return false;
        }
        return true;
    }
}