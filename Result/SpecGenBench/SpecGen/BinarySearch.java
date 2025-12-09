public class BinarySearch {

    //@ requires arr != null;
    //@ requires (\forall int i; 0 <= i && i + 1 < arr.length; arr[i] <= arr[i + 1]);
    //@ ensures arr.length == 0 ==> \result == -1;
    //@ ensures (\result == -1) ==> (\forall int i; 0 <= i && i < arr.length; arr[i] != key);
    //@ ensures (\result != -1) ==> (0 <= \result && \result < arr.length && arr[\result] == key);
    //@ assignable \nothing;
    public static int Binary(int[] arr, int key) {
        if (arr.length == 0) {
            return -1;
        } else {
            int low = 0;
            int high = arr.length;
            int mid =  high / 2;

            //@ maintaining 0 <= low && low <= high && high <= arr.length;
            //@ maintaining (\forall int i; 0 <= i && i < low; arr[i] < key);
            //@ maintaining (\forall int i; high <= i && i < arr.length; arr[i] > key);
            //@ maintaining low <= mid && mid < high;
            //@ decreases high - low;
            while (low < high && arr[mid] != key) {
                if (arr[mid] < key) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
                mid = low + (high - low) / 2;
            }
            if (low >= high) {
                return -1;
            }
            return mid;
        }
    }
}