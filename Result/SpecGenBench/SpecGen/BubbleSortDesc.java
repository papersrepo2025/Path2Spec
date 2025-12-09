public class BubbleSortDesc { 
    
    //@ requires array != null;
    //@ requires 0 <= x && x < array.length && 0 <= y && y < array.length;
    //@ assignable array[x], array[y];
    //@ ensures array[x] == \old(array[y]) && array[y] == \old(array[x]);
    //@ ensures (\forall int k; 0 <= k && k < array.length; (k != x && k != y) ==> array[k] == \old(array[k]));
    void swap(int x, int y,  int array[]) {
        int temp;     
        temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //@ requires arr != null;
    //@ assignable arr[*];
    //@ ensures \result == arr;
    //@ ensures arr.length == \old(arr.length);
    //@ ensures (\forall int k; 0 <= k && k < arr.length - 1; arr[k] >= arr[k+1]);
    //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < arr.length; arr[i] == v)
    //@                     == (\num_of int i; 0 <= i && i < \old(arr.length); \old(arr[i]) == v));
    int[] bubbleSort(int arr[]) {
        int n = arr.length;

        //@ maintaining n == arr.length && 0 <= i && i <= n - 1;
        //@ maintaining (\forall int p; 0 <= p && p < n - i; (\forall int q; n - i <= q && q < n; arr[p] >= arr[q]));
        //@ decreases (n - 1) - i;
        for (int i = 0; i < n-1; i++) {	

            //@ maintaining n == arr.length && 0 <= j && j <= n - i - 1;
            //@ maintaining (\forall int k; 0 <= k && k < j; arr[k] >= arr[k+1]);
            //@ maintaining (\forall int p; 0 <= p && p < n - i; (\forall int q; n - i <= q && q < n; arr[p] >= arr[q]));
            //@ decreases (n - i - 1) - j;
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j+1] > arr[j]) {  
		     swap(j, j + 1, arr); 
                } 
	    }
	} 
	return arr;
    } 
}