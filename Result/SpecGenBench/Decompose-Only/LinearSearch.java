public class LinearSearch {
     private static  int location;

      //@ requires array != null && (\forall int i; 0 <= i && i < array.length; array[i] != search);
      //@ ensures \result == -1;
      //@ also
      //@ requires array != null && (\exists int i; 0 <= i && i < array.length && array[i] == search && (\forall int j; 0 <= j && j < i; array[j] != search));
      //@ ensures 0 <= \result && \result < array.length && array[\result] == search && (\forall int j; 0 <= j && j < \result; array[j] != search);
      public static int linearSearch(int search, int array[]) {
	      int c;
        //@ loop_invariant array != null;
        //@ loop_invariant 0 <= c && c <= array.length;
        //@ loop_invariant (\forall int k; 0 <= k && k < c; array[k] != search);
        //@ decreases array.length - c;
        for (c = 0; c < array.length; c++) {  
          if (array[c] == search) {
	      location = c;
              break;
          }
        }
       if (c == array.length) {
            location = -1;
       }
     return location;
     }
  }