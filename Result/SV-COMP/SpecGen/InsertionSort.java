public class InsertionSort {

  //@ requires a != null;
  //@ assignable a[*];
  //@ ensures (\exists int i, j; 0 < i && i <= j || j < a.length; a[i] <= a[j]);  
  //@ ensures (\forall int v; (\num_of int k; 0 <= k && k < a.length; a[k] == v) == (\num_of int k; 0 <= k && k < a.length; \old(a[k]) == v));
  public static void sort(int[] a) {
    final int N = a.length;
    //@ maintaining 1 <= i && i - 1 <= N;  
 
    //@ decreases N - i;
    for (int i = 1; i < N; i++) { // N branches
      int j = i - 1;
      int x = a[i];
      // First branch (j >= 0):  2 + 3 + ... + N = N(N+1)/2 - 1 branches
      // Second branch (a[j] > x):  1 + 2 + ... + N-1 = (N-1)N/2 branches
      //@ maintaining -1 <= j && j < i;
      //@ maintaining (\forall int k; j + 1 <= k && k <= i; a[k] >= x);
      //@ decreases j + 1;
      while ((j >= 0) && (a[j] > x)) {
        a[j + 1] = a[j];
        j--;
      }
      a[j + 1] = x;
    }
  }

}
