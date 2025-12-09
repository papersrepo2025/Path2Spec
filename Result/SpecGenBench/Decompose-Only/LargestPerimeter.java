import java.util.Arrays;

class LargestPerimeter {
        //@ requires A != null;
        //@ ensures \result == 0;
        //@ ensures A.length == \old(A.length);
        //@ also
        //@ requires A == null;
        //@ signals (NullPointerException e) true;
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
            //@ maintaining -1 <= i && i <= A.length - 1;
            //@ decreases i;
        for (int i = A.length - 1; i >= 2; --i) {
            //@ assume i != 0;
            //@ assume !(A[i - 2] + A[i - 1] > A[i]);
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}