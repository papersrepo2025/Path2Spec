
public class Swap {
    //@ requires arr != null;
//@ requires 0 <= a && a < arr.length;
//@ requires 0 <= b && b < arr.length;
//@ ensures arr[a] == \old(arr[b]);
//@ ensures arr[b] == \old(arr[a]);
//@ ensures (\forall int i; i != a && i != b ==> arr[i] == \old(arr[i]));
    public static void swap (int[] arr, int a, int b) {
        if(a >= arr.length || a < 0 || b >= arr.length || b < 0) {
            return;
        }
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
