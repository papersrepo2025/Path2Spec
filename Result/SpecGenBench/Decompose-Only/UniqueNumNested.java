public class UniqueNumNested {

    //@ requires arr != null;
    //@ requires arr.length == 0;
    //@ ensures \result == -1;
    //@ also
    //@ requires arr != null;
    //@ requires arr.length > 0 && (\exists int k; 0 <= k && k < arr.length && (\forall int j; 0 <= j &&  j < arr.length; (j == k) || (arr[j] != arr[k])) && (\forall int i; 0 <= i && i < k; (\exists int j; 0 <= j && j < arr.length && j != i && arr[j] == arr[i])));
    //@ ensures (\exists int k; 0 <= k && k < arr.length && (\forall int j; 0 <= j &&  j < arr.length; (j == k) || (arr[j] != arr[k])) && (\forall int i; 0 <= i && i < k; (\exists int j; 0 <= j && j < arr.length && j != i && arr[j] == arr[i])) && \result == k);
    //@ also
    //@ requires arr != null;
    //@ requires arr.length > 0 && (\forall int i; 0 <= i && i < arr.length; (\exists int j; 0 <= j && j < arr.length && j != i && arr[j] == arr[i])));
    //@ ensures \result == -1;
    public static int uniqueNum(int[] arr) {
        //@ maintaining 0 <= i && i <= arr.length;
        //@ decreases arr.length - i;
        for (int i = 0; i < arr.length; ++i) {
            int j = 0;
            //@ maintaining 0 <= i && i < arr.length;
            //@ maintaining 0 <= j && j <= arr.length;
            //@ decreases arr.length - j;
            while(j < arr.length) {
                if(i != j && arr[i] == arr[j])
                    break;
                j++;
            }
            if(j == arr.length)
                return i;
        }
        return -1;
    }
    
}