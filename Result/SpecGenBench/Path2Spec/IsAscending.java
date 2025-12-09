
public class IsAscending {
    //@ requires arr != null;
//@ ensures \result == (\forall int i; 0 <= i && i < arr.length - 1; arr[i] < arr[i+1]);
    public boolean isAscending (int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        //@ loop_invariant 0 <= i && i < n;
//@ loop_invariant (\forall int j; 0 <= j && j < i; arr[j] < arr[j+1]);
//@ decreases n - i;
        for (int i = 0; i < n - 1; i++) {
            if(arr[i] >= arr[i+1])
                return false;
        }
        return true;
    }
}
