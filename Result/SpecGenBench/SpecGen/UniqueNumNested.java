public class UniqueNumNested {

    /*@ requires arr != null;
      @ assignable \nothing;
 
      @                                  (\forall int j; 0 <= j && j < arr.length; j == i || arr[i] != arr[j]));
      @ ensures (0 <= \result && \result < arr.length) ==>
      @           ((\forall int j; 0 <= j && j < arr.length; j == \result || arr[\result] != arr[j])
      @            && (\forall int k; 0 <= k && k < \result;
      @                   (\exists int j; 0 <= j && j < arr.length && j != k && arr[k] == arr[j])));
      @*/
    public static int uniqueNum(int[] arr) {
        //@ maintaining 0 <= i && i <= arr.length;
        //@ maintaining (\forall int k; 0 <= k && k < i;
        //@                (\exists int j; 0 <= j && j < arr.length && j != k && arr[k] == arr[j]));
        //@ decreases arr.length - i;
        for (int i = 0; i < arr.length; ++i) {
            int j = 0;
            //@ maintaining 0 <= j && j <= arr.length;
            //@ maintaining (\forall int t; 0 <= t && t < j; t == i || arr[i] != arr[t]);
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
