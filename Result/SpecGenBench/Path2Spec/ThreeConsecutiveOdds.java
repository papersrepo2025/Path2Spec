
public class ThreeConsecutiveOdds {

    //@ requires arr != null;
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;

        //@ maintaining 0 <= i && i <= n;
//@ decreases n - i;
        for (int i = 0; i <= n - 3; ++i) {
            if ((arr[i] & 1) != 0 && (arr[i + 1] & 1) != 0 && (arr[i + 2] & 1) != 0) {
                return true;
            }
        }
        return false;
    }
}
