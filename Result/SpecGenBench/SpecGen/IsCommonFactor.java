public class IsCommonFactor {
    /*@ public normal_behavior
      @   requires factor != 0;
      @   assignable \nothing;
      @   ensures \result <==> (a % factor == 0 && b % factor == 0);
      @*/
    public boolean isCommonFactor (int a, int b, int factor) {
        return a % factor == 0 && b % factor == 0;
    }
}