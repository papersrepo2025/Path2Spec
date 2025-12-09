public class SelectionSort {

    //@ requires arr != null;
    //@ assignable arr[*];
    //@ ensures (\forall int k; 0 <= k && k + 1 < arr.length; arr[k] <= arr[k+1]);
    //@ ensures (\forall int k; 0 <= k && k < arr.length; (\exists int j; 0 <= j && j < arr.length; arr[k] == \old(arr)[j]))
    //@      && (\forall int j; 0 <= j && j < arr.length; (\exists int k; 0 <= k && k < arr.length; arr[k] == \old(arr)[j]));
    public static void selectionSort(int[] arr){  
        //@ maintaining 0 <= i && i <= arr.length - 1;
        //@ maintaining (\forall int a; 0 <= a && a + 1 < i; arr[a] <= arr[a+1]);
        //@ maintaining (\forall int a; 0 <= a && a < i; (\forall int b; i <= b && b < arr.length; arr[a] <= arr[b]));
        //@ decreases (arr.length - 1) - i;
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            //@ maintaining i + 1 <= j && j <= arr.length;
            //@ maintaining i <= index && index < j;
            //@ maintaining (\forall int k; i <= k && k < j; arr[index] <= arr[k]);
            //@ decreases arr.length - j;
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j] < arr[index]){  
                    index = j;
                }  
            }  
            int smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
    }
    
}