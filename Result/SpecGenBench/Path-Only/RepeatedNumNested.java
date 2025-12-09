
public class RepeatedNumNested {
    //@ requires arr != null;
    //@ requires arr.length == 1;
    //@ ensures \result == -1;
    //@ also
    //@ requires arr != null;
    //@ requires arr.length > 0;
    //@ requires (\forall int i, j; 0 <= i && i < j && j < arr.length; arr[i] != arr[j]);
    //@ ensures \result == -1;
    //@ also
    //@ requires arr != null && arr.length > 1;
    //@ ensures \result == -1 || (0 <= \result && \result < arr.length && (\exists int k; \result < k && k < arr.length; arr[\result] == arr[k]));
    public static int repeatedNum(int[] arr) {
        //@ loop_invariant 0 <= i && i <= arr.length;
        //@ decreases arr.length - i;
        for (int i = 0; i < arr.length; ++i) {
            //@ loop_invariant i + 1 <= j && j <= arr.length;
            //@ decreases arr.length - j;
            for (int j = i + 1; j < arr.length; ++j) {
                if(arr[i] == arr[j])
                    return i;
            }
        }
        return -1;
    }
    
}
