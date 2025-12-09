
public class IsDescending {
    //@ requires arr == null;
    //@ signals (NullPointerException e) true;
    //@ assignable \nothing;
    //@ also
    //@ requires arr != null && arr.length < 2;
    //@ ensures \result == true;
    //@ assignable \nothing;
    //@ also
    //@ requires arr != null && arr.length >= 2 && (\exists int i; 0 <= i && i < arr.length - 1; arr[i] <= arr[i+1]);
    //@ ensures \result == false;
    //@ assignable \nothing;
    //@ also
    //@ requires arr != null && arr.length >= 2 && (\forall int i; 0 <= i && i < arr.length - 1; arr[i] > arr[i+1]);
    //@ signals (ArrayIndexOutOfBoundsException e) true;
    //@ assignable \nothing;
    public boolean isDescending (int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining (\forall int k; 0 <= k && k < i && k < n - 1; arr[k] > arr[k+1]);
        //@ decreases n - i;
        for (int i = 0; i < n; i++) {
            //@ assume i != 0;
            //@ assume i + 1 < n;
            if(arr[i] <= arr[i+1])
                return false;
        }
        return true;
    }
}