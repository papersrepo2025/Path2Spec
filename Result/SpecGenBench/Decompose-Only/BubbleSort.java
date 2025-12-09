public class BubbleSort { 
    
    //@ requires array != null;
    //@ requires 0 <= x && x < array.length;
    //@ requires 0 <= y && y < array.length;
    //@ requires Integer.MIN_VALUE <= x && x <= Integer.MAX_VALUE;
    //@ requires Integer.MIN_VALUE <= y && y <= Integer.MAX_VALUE;
    //@ ensures array[x] == \old(array[y]) && array[y] == \old(array[x]);
    //@ ensures (\forall int k; 0 <= k && k < array.length && k != x && k != y; array[k] == \old(array[k]));
    //@ assignable array[*];
    void swap(int x, int y,  int array[]) {
        int temp;     
        temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //@ requires arr != null;
    //@ requires 4 <= arr.length;
    //@ requires Integer.MIN_VALUE <= arr.length && arr.length <= Integer.MAX_VALUE - 1;
    //@ ensures \result == arr;
    //@ ensures \result != null;
    //@ ensures \result.length == \old(arr.length);
    //@ assignable arr[*];
    int[] bubbleSort(int arr[]) {
        int n = arr.length;

        // Execute first iteration (i = 0)
        {
            int i = 0;
            {
                int j = 0;
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
            {
                int j = 1;
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
            {
                int j = 2;
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
            //@ maintaining arr != null && n >= 4 && 0 <= i && i < n && 0 <= j && j <= n - i - 1;
            //@ decreases (n - i - 1) - j;
            for (int j = 3; j < n - i - 1; j++) {
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
        }

        // Execute second iteration (i = 1)
        {
            int i = 1;
            //@ maintaining arr != null && n >= 4 && 0 <= j && j <= n - i - 1 && (j < n - i - 1 ==> 0 <= j && j + 1 < n) && Integer.MIN_VALUE <= j + 1 && j + 1 <= Integer.MAX_VALUE;
            //@ decreases (n - i - 1) - j;
            for (int j = 0; j < n - i - 1; j++) {
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
        }

        // Execute third iteration (i = 2)
        {
            int i = 2;
            //@ maintaining arr != null && n >= 4 && 0 <= j && j <= n - i - 1 && (j < n - i - 1 ==> 0 <= j && j + 1 < n) && Integer.MIN_VALUE <= j + 1 && j + 1 <= Integer.MAX_VALUE;
            //@ decreases (n - i - 1) - j;
            for (int j = 0; j < n - i - 1; j++) {
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
        }

        // Preserve remaining iterations of the outer loop
        //@ maintaining arr != null && n >= 4 && 3 <= i && i <= n - 1 && Integer.MIN_VALUE <= i + 1 && i + 1 <= Integer.MAX_VALUE;
        //@ decreases (n - 1) - i;
        for (int i = 3; i < n - 1; i++) {    
            //@ maintaining arr != null && n >= 4 && 0 <= j && j <= n - i - 1 && (j < n - i - 1 ==> 0 <= j && j + 1 < n) && Integer.MIN_VALUE <= j + 1 && j + 1 <= Integer.MAX_VALUE;
            //@ decreases (n - i - 1) - j;
            for (int j = 0; j < n - i - 1; j++) {
                //@ assert 0 <= j && j + 1 < n;
                if (arr[j + 1] < arr[j]) {  
                    swap(j, j + 1, arr); 
                } 
            }
        } 
        return arr;
    } 
}