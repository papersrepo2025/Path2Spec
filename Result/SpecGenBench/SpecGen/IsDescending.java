public class IsDescending {
    //@ requires arr != null;
    //@ assignable \nothing;
    //@ ensures \result <==> arr.length < 2;
    //@ ensures \result == false ==> (\exists int i; 0 <= i && i < arr.length - 1; arr[i] <= arr[i+1]);
    //@ signals (ArrayIndexOutOfBoundsException e) (arr.length >= 2 && (\forall int i; 0 <= i && i < arr.length - 1; arr[i] > arr[i+1]));
    public boolean isDescending (int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        /*@ 
          @ maintaining 0 <= i && i <= n;
          @ maintaining n == arr.length;
          @ maintaining (\forall int k; 0 <= k && k + 1 < i; arr[k] > arr[k+1]);
          @ decreases n - i;
          @*/
        for (int i = 0; i < n; i++) {
            if(arr[i] <= arr[i+1])
                return false;
        }
        return true;
    }
}