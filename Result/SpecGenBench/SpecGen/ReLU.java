public class ReLU {
    /*@ public normal_behavior
      @   ensures (x >= 0.0) ==> \result == x;
      @   ensures !(x >= 0.0) ==> \result == 0.0;
      @   ensures \result >= 0.0;
      @   assignable \nothing;
      @*/
    public static double computeReLU(double x) {
        if(x >= 0) {
            return x;
        }
        return 0.0;
    }
}