public class BubbleSort { 
    
    //@ requires array != null;
    //@ requires 0 <= x && x < array.length;
    //@ requires 0 <= y && y < array.length;
    //@ assignable array[*];
    //@ ensures array[x] == \old(array[y]) && array[y] == \old(array[x]);
    //@ ensures (\forall int k; 0 <= k && k < array.length && k != x && k != y; array[k] == \old(array[k]));
    void swap(int x, int y,  int array[]) {
        int temp;     
        temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //@ requires arr != null;
    //@ assignable arr[*];
    //@ ensures \result == arr;
    //@ ensures (\forall int i; 0 <= i && i < arr.length - 1; arr[i] <= arr[i+1]);
    //@ ensures (\forall int v; (\num_of int k; 0 <= k && k < arr.length; arr[k] == v)
    //@                      == (\num_of int k; 0 <= k && k < arr.length; \old(arr[k]) == v));
    int[] bubbleSort(int arr[]) {
        int n = arr.length;

        //@ maintaining 0 <= i && i <= n;
        //@ maintaining (\forall int t; n - i <= t && t < n - 1; arr[t] <= arr[t+1]);
        //@ maintaining (\forall int p; 0 <= p && p < n - i; (\forall int s; n - i <= s && s < n; arr[p] <= arr[s]));
        //@ decreases (n - 1) - i;
        for (int i = 0; i < n-1; i++) {	

            //@ maintaining 0 <= j && j <= n;
            //@ maintaining (\forall int k; 0 <= k && k <= j; arr[k] <= arr[j]);
            //@ maintaining (\forall int t; n - i <= t && t < n - 1; arr[t] <= arr[t+1]);
            //@ maintaining (\forall int p; 0 <= p && p < n - i; (\forall int s; n - i <= s && s < n; arr[p] <= arr[s]));
            //@ decreases (n - i - 1) - j;
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j+1] < arr[j]) {  
		     swap(j, j + 1, arr); 
                } 
	    }
	} 
	return arr;
    } 
}