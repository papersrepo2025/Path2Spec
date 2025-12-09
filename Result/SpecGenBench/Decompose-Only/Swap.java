

public class Swap {

    //@ requires arr == null;
    //@ assignable \nothing;
    //@ signals (NullPointerException) true;
    //@ also
    //@ requires arr != null && (a < 0 || a >= arr.length || b < 0 || b >= arr.length);
    //@ assignable \nothing;
    //@ ensures (\forall int i; 0 <= i && i < \old(arr.length); arr[i] == \old(arr[i]));
    //@ also
    //@ requires arr != null && 0 <= a && a < arr.length && 0 <= b && b < arr.length;
    //@ assignable arr[*];
    //@ ensures arr[a] == \old(arr[b]);
    //@ ensures arr[b] == \old(arr[a]);
    //@ ensures (\forall int i; 0 <= i && i < arr.length; (i != a && i != b) ==> arr[i] == \old(arr[i]));
    public static void swap (int[] arr, int a, int b) {
        if(a >= arr.length || a < 0 || b >= arr.length || b < 0) {
            return;
        }
        //@ assert arr != null && 0 <= a && a < arr.length;
        int temp = arr[a];
        //@ assert arr != null && 0 <= a && a < arr.length && 0 <= b && b < arr.length;
        arr[a] = arr[b];
        //@ assert arr != null && 0 <= b && b < arr.length;
        arr[b] = temp;
    }
}