
public class IsDescending {
    //@ requires arr != null && 0 <= arr.length && arr.length < 2;
//@ ensures \result == true;
    public boolean isDescending(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if(arr[i] <= arr[i+1])
                return false;
        }
        return true;
    }
}
