public class LinearSearch {
     /*@ spec_public @*/ private static  int location;

      //@ requires array != null;
      //@ assignable location;
      //@ ensures (\result == -1) <==> (\forall int k; 0 <= k && k < array.length; array[k] != search);
      //@ ensures \result != -1 ==> (0 <= \result && \result < array.length && array[\result] == search && (\forall int k; 0 <= k && k < \result; array[k] != search));
      //@ ensures location == \result;
      public static int linearSearch(int search, int array[]) {
	      int c;

        //@ maintaining 0 <= c && c <= array.length;
        //@ maintaining (\forall int k; 0 <= k && k < c; array[k] != search);
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