public class ThreeConsecutiveOdds {

    /*@
      @ requires arr != null;
      @ assignable \nothing;
 
      @                           ((arr[i] & 1) != 0) && ((arr[i+1] & 1) != 0) && ((arr[i+2] & 1) != 0));
      @*/
    public /*@ pure @*/ boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;

        /*@
          @ maintaining 0 <= i && i <= n;
          @ maintaining (\forall int j; 0 <= j && j + 2 < i;
          @                   ((arr[j] & 1) == 0) || ((arr[j+1] & 1) == 0) || ((arr[j+2] & 1) == 0));
          @ decreases n - i;
          @*/
        for (int i = 0; i <= n - 3; ++i) {
            if ((arr[i] & 1) != 0 && (arr[i + 1] & 1) != 0 && (arr[i + 2] & 1) != 0) {
                return true;
            }
        }
        return false;
    }
}
