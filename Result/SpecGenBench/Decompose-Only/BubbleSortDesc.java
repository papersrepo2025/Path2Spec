public class BubbleSortDesc {
    
    //@ requires array != null;
    //@ requires 0 <= x && x < array.length;
    //@ requires 0 <= y && y < array.length;
    //@ assignable array[*];
    //@ ensures array[x] == \old(array[y]);
    //@ ensures array[y] == \old(array[x]);
    //@ ensures (\forall int k; 0 <= k && k < array.length && k != x && k != y; array[k] == \old(array[k]));
    void swap(int x, int y, int array[]) {
        int temp;
        temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //@ requires arr != null;
    //@ assignable \nothing;
    //@ ensures \result == arr;
    //@ ensures (\forall int k; 0 <= k && k < arr.length; \result[k] == \old(arr[k]));
    int[] bubbleSort(int arr[]) {
        int n = arr.length;

        return arr;
    }
}