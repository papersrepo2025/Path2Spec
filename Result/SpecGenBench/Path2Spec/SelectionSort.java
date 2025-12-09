
public class SelectionSort {

    //@ requires arr != null;
    //@ ensures arr.length <= 1 ==>
    //@ (\forall int i; 0 <= i && i < arr.length; arr[i] == \old(arr[i]));
    public static void selectionSort(int[] arr){  
        if (arr.length <= 1) return;
        //@ maintaining 0 <= i && i <= arr.length - 1;
//@ decreases arr.length - i;
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            //@ maintaining i + 1 <= j && j <= arr.length;
//@ decreasing arr.length - j;
//@ maintaining \forall int k; i <= k && k < j; arr[index] <= arr[k];
//@ maintaining i <= index && index < arr.length;
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
