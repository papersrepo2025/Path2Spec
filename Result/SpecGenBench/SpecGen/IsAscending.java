public class IsAscending {
    //@ ensures arr.length < 2 ==> \result;
    //@ ensures \result ==> (\forall int i; 0 <= i && i + 1 < arr.length; arr[i] < arr[i+1]);
    //@ ensures !\result ==> (\exists int i; 0 <= i && i + 1 < arr.length; arr[i] >= arr[i+1]);
    public boolean isAscending (int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining (\forall int j; 0 <= j && j + 1 < i; arr[j] < arr[j+1]);
        //@ decreases n - i;
        for (int i = 0; i < n; i++) {
            if(arr[i] >= arr[i+1])
                return false;
        }
        return true;
    }
}