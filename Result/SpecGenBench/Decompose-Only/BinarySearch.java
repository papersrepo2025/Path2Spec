
public class BinarySearch {

    //@ requires arr == null;
    //@ assignable \nothing;
    //@ signals (NullPointerException e) true;
    //@ also requires arr != null && (\forall int j; 0 <= j && j < arr.length; (\forall int i; 0 <= i && i < j; arr[i] <= arr[j]));
    //@ assignable \nothing;
    //@ ensures arr.length == 0 ==> \result == -1;
    //@ ensures arr.length > 0 ==> ((\exists int k; 0 <= k && k < arr.length && arr[k] == key) ==> (0 <= \result && \result < arr.length && arr[\result] == key));
    //@ ensures arr.length > 0 ==> ((\forall int i; 0 <= i && i < arr.length; arr[i] != key) ==> \result == -1);
    public static int Binary(int[] arr, int key) {
        if (arr.length == 0) {
            //@ assert arr.length == 0;
            return -1;
        } else {
            //@ assert arr.length >= 0;
            int low = 0;
            //@ assert low == 0;
            int high = arr.length;
            //@ assert 0 <= high && high <= arr.length;
            //@ assert 2 != 0;
            int mid =  high / 2;

            //@ loop_invariant 0 <= low && low <= high && high <= arr.length;
            //@ loop_invariant (\forall int i; 0 <= i && i < low; arr[i] < key);
            //@ loop_invariant (\forall int i; high <= i && i < arr.length; key < arr[i]);
            //@ loop_invariant mid == low + (high - low) / 2;
            //@ decreases high - low;
            while (low < high && arr[mid] != key) {
                if (arr[mid] < key) {
                    //@ assert mid + 1 >= 0;
                    low = mid + 1;
                } else {
                    //@ assert 0 <= mid && mid <= high;
                    high = mid;
                }
                //@ assert high - low >= 0 || low <= high;
                //@ assert 2 != 0;
                mid = low + (high - low) / 2;
            }
            if (low >= high) {
                //@ assert (\forall int i; 0 <= i && i < arr.length; arr[i] != key);
                return -1;
            }
            //@ assert 0 <= mid && mid < arr.length;
            //@ assert arr[mid] == key;
            return mid;
        }
    }
}