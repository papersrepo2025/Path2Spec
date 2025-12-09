public class IsAscending {
    /*@ 
      @ public normal_behavior
      @   requires arr != null && arr.length < 2;
      @   assignable \nothing;
      @   ensures \result == true;
      @ also
      @ public normal_behavior
      @   requires arr != null && arr.length >= 2 &&
      @            (\exists int k; 0 <= k && k <= arr.length - 2;
      @                (\forall int i; 0 <= i && i < k; arr[i] < arr[i+1]) &&
      @                arr[k] >= arr[k+1]);
      @   assignable \nothing;
      @   ensures \result == false;
      @ also
      @ public exceptional_behavior
      @   requires arr == null;
      @   assignable \nothing;
      @   signals (NullPointerException) true;
      @ also
      @ public exceptional_behavior
      @   requires arr != null && arr.length >= 2 &&
      @            (\forall int i; 0 <= i && i <= arr.length - 2; arr[i] < arr[i+1]);
      @   assignable \nothing;
      @   signals (ArrayIndexOutOfBoundsException) true;
      @*/
    public boolean isAscending (/*@ nullable @*/ int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        /*@ loop_invariant 0 <= i && i <= n;
          @ decreases n - i;
          @*/
        for (int i = 0; i < n; i++) {
            if(arr[i] >= arr[i+1])
                return false;
        }
        return true;
    }
}