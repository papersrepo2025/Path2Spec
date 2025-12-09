public class SelectionSortDesc {

    //@ requires arr != null;
    //@ assignable arr[*];
    //@ ensures arr.length == \old(arr.length);
    //@ ensures (\forall int k; 0 <= k && k + 1 < arr.length; arr[k] >= arr[k+1]);
    //@ ensures (\forall int v; (\num_of int k; 0 <= k && k < arr.length; arr[k] == v) == (\num_of int k; 0 <= k && k < arr.length; \old(arr[k]) == v));
    public static void selectionSort(int[] arr){  
        //@ maintaining 0 <= i && i <= arr.length;
        //@ maintaining (\forall int a; 0 <= a && a + 1 < i; arr[a] >= arr[a+1]);
        //@ maintaining (\forall int a,b; 0 <= a && a < i && i <= b && b < arr.length; arr[a] >= arr[b]);
        //@ decreases (arr.length - 1) - i;
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            //@ maintaining i + 1 <= j && j <= arr.length;
            //@ maintaining i <= index && index < j;
            //@ maintaining (\forall int k; i <= k && k < j; arr[index] >= arr[k]);
            //@ decreases arr.length - j;
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j] > arr[index]){  
                    index = j;
                }  
            }  
            int smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
    }
    
}