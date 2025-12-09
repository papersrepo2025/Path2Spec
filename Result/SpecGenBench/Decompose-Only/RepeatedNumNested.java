
public class RepeatedNumNested {

    /*@ requires arr != null;
      @ {|
      @   requires (\forall int a; 0 <= a && a < arr.length; (\forall int b; a + 1 <= b && b < arr.length; arr[a] != arr[b]));
      @   ensures \result == -1;
      @ also
      @   requires (\exists int a; 0 <= a && a < arr.length; (\exists int b; a + 1 <= b && b < arr.length; arr[a] == arr[b]));
      @   ensures 0 <= \result && \result < arr.length;
      @   ensures (\exists int j; \result + 1 <= j && j < arr.length; arr[\result] == arr[j]);
      @   ensures (\forall int k; 0 <= k && k < \result; (\forall int l; k + 1 <= l && l < arr.length; arr[k] != arr[l]));
      @ |}
      @*/
    public static /*@ pure @*/ int repeatedNum(int[] arr) {
        //@ loop_invariant 0 <= i && i <= arr.length;
        //@ loop_invariant (\forall int k; 0 <= k && k < i; (\forall int l; k + 1 <= l && l < arr.length; arr[k] != arr[l]));
        //@ decreases arr.length - i;
        for (int i = 0; i < arr.length; ++i) {
            //@ assume i != 0;
            //@ loop_invariant i + 1 <= j && j <= arr.length;
            //@ loop_invariant 0 <= i && i < arr.length;
            //@ loop_invariant (\forall int t; i + 1 <= t && t < j; arr[i] != arr[t]);
            //@ decreases arr.length - j;
            for (int j = i + 1; j < arr.length; ++j) {
                //@ assume i != 0;
                if(arr[i] == arr[j])
                    return i;
            }
        }
        return -1;
    }
    
}