public class ReLU {
    /*@ public normal_behavior
      @   requires x >= 0.0;
      @   assignable \nothing;
      @   ensures \result == x;
      @ also
      @ public normal_behavior
      @   requires !(x >= 0.0);
      @   assignable \nothing;
      @   ensures \result == 0.0;
      @*/
    public static double computeReLU(double x) {
        if(x >= 0) {
            return x;
        }
        return 0.0;
    }
}