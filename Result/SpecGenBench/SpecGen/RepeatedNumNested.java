public class RepeatedNumNested {

    /*@ requires arr != null;
      @ assignable \nothing;
      @ ensures (\result == -1) <==>
      @           (\forall int p; 0 <= p && p < arr.length;
      @             (\forall int q; p < q && q < arr.length; arr[p] != arr[q]));
      @ ensures \result != -1 ==>
      @           (0 <= \result && \result < arr.length) &&
      @           (\exists int j; \result < j && j < arr.length; arr[\result] == arr[j]) &&
      @           (\forall int p; 0 <= p && p < \result;
      @              (\forall int q; p < q && q < arr.length; arr[p] != arr[q]));
      @*/
    public static int repeatedNum(int[] arr) {
        /*@ maintaining 0 <= i && i <= arr.length;
          @ maintaining (\forall int p; 0 <= p && p < i;
          @               (\forall int q; p < q && q < arr.length; arr[p] != arr[q]));
          @ decreases arr.length - i;
          @*/
        for (int i = 0; i < arr.length; ++i) {
            /*@ maintaining i + 1 <= j && j <= arr.length;
              @ maintaining 0 <= i && i < arr.length;
              @ maintaining (\forall int k; i < k && k < j; arr[i] != arr[k]);
              @ decreases arr.length - j;
              @*/
            for (int j = i + 1; j < arr.length; ++j) {
                if(arr[i] == arr[j])
                    return i;
            }
        }
        return -1;
    }
    
}