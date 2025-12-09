public class MergeSortIterative {

  /////////////////////////////////////////
  // Iterative mergeSort
  /////////////////////////////////////////

  /*@ spec_public @*/
  //@ requires a != null;
  //@ ensures a != null && a.length == \old(a.length);
  //@ ensures (\forall int i; 0 <= i && i+1 < a.length; a[i] <= a[i+1]);
  //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < a.length; a[i] == v) == (\old(\num_of int i; 0 <= i && i < a.length; a[i] == v)));
  public static void iterativeMergesort(int[] a) {
    int[] aux = new int[a.length];
    //@ maintaining a != null && aux != null && a.length == aux.length;
    //@ maintaining a.length == \old(a.length);
    //@ maintaining 1 <= blockSize && blockSize <= (a.length == 0 ? 1 : a.length);
    //@ decreases a.length - blockSize;
    for (int blockSize = 1; blockSize < a.length; blockSize *= 2)
      //@ maintaining 0 <= start && start <= a.length;
      //@ maintaining a != null && aux != null && a.length == aux.length;
      //@ decreases a.length - start;
      for (int start = 0; start < a.length; start += 2 * blockSize)
        merge(a, aux, start, start + blockSize, start + 2 * blockSize);
  }

  /////////////////////////////////////////
  // Iterative mergeSort without copy
  /////////////////////////////////////////

  /*@ spec_public @*/
  //@ requires a != null;
  //@ ensures a != null && a.length == \old(a.length);
  //@ ensures (\forall int i; 0 <= i && i+1 < a.length; a[i] <= a[i+1]);
  //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < a.length; a[i] == v) == (\old(\num_of int i; 0 <= i && i < a.length; a[i] == v)));
  public static void iterativeMergesortWithoutCopy(int[] a) {
    int[] from = a, to = new int[a.length];
    //@ maintaining a != null && from != null && to != null && from.length == to.length && from.length == a.length;
    //@ maintaining 1 <= blockSize && blockSize <= (a.length == 0 ? 1 : a.length);
    //@ decreases a.length - blockSize;
    for (int blockSize = 1; blockSize < a.length; blockSize *= 2) {
      //@ maintaining 0 <= start && start <= a.length;
      //@ maintaining from != null && to != null && from.length == to.length && from.length == a.length;
      //@ decreases a.length - start;
      for (int start = 0; start < a.length; start += 2 * blockSize)
        mergeWithoutCopy(from, to, start, start + blockSize, start + 2 * blockSize);
      int[] temp = from;
      from = to;
      to = temp;
    }
    if (a != from)
      // copy back
      //@ maintaining 0 <= k && k <= a.length;
      //@ maintaining (\forall int i; 0 <= i && i < k; a[i] == from[i]);
      //@ decreases a.length - k;
      for (int k = 0; k < a.length; k++) a[k] = from[k];
  }

  /*@ spec_public @*/
  //@ requires from != null && to != null;
  //@ requires 0 <= lo && lo <= mid && lo <= hi;
  //@ ensures to != null && from != null;
  //@ ensures (\forall int t; 0 <= t && t < lo; to[t] == \old(to[t]));
  //@ ensures (\forall int t; (hi > from.length ? from.length : hi) <= t && t < to.length; to[t] == \old(to[t]));
  //@ ensures (\forall int i; lo <= i && i+1 < (hi > from.length ? from.length : hi); to[i] <= to[i+1]);
  //@ ensures (\forall int v;
  //@            (\num_of int i; lo <= i && i < (hi > from.length ? from.length : hi); to[i] == v)
  //@          == (\num_of int i; lo <= i && i < (hi > from.length ? from.length : hi); from[i] == v));
  private static void mergeWithoutCopy(int[] from, int[] to, int lo, int mid, int hi) {
    // DK: cannot just return if mid >= a.length, but must still copy remaining elements!
    // DK: add two tests to first verify "mid" and "hi" are in range
    if (mid > from.length) mid = from.length;
    if (hi > from.length) hi = from.length;
    int i = lo, j = mid;
    //@ maintaining lo <= k && k <= hi;
    //@ maintaining lo <= i && i <= mid;
    //@ maintaining mid <= j && j <= hi;
    //@ maintaining k - lo == (i - lo) + (j - mid);
    //@ maintaining (\forall int t; lo <= t && t+1 < k; to[t] <= to[t+1]);
    //@ decreases hi - k;
    for (int k = lo; k < hi; k++) {
      if (i == mid) to[k] = from[j++];
      else if (j == hi) to[k] = from[i++];
      else if (from[j] < from[i]) to[k] = from[j++];
      else to[k] = from[i++];
    }
    // DO NOT copy back
    // for (int k = lo; k < hi; k++)
    //   a[k] = aux[k];
  }

  /////////////////////////////////////////
  // Recursive mergeSort, adapted from:
  // Sedgewick and Wayne, Introduction to Programming in Java
  // http://www.cs.princeton.edu/introcs/42sort/MergeSort.java.html
  /////////////////////////////////////////

  /*@ spec_public @*/
  //@ requires a != null && aux != null;
  //@ requires 0 <= lo && lo <= mid && lo <= hi;
  //@ ensures (mid >= a.length) ==> (\forall int t; 0 <= t && t < a.length; a[t] == \old(a[t]));
  //@ ensures (mid < a.length) ==> (
  //@   (\forall int t; 0 <= t && t < lo; a[t] == \old(a[t])) &&
  //@   (\forall int t; (hi > a.length ? a.length : hi) <= t && t < a.length; a[t] == \old(a[t])) &&
  //@   (\forall int i; lo <= i && i+1 < (hi > a.length ? a.length : hi); a[i] <= a[i+1]) &&
  //@   (\forall int v;
  //@      (\num_of int i; lo <= i && i < (hi > a.length ? a.length : hi); a[i] == v)
  //@    == (\old(\num_of int i; lo <= i && i < (hi > a.length ? a.length : hi); a[i] == v)))
  //@ );
  private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
    // DK: add two tests to first verify "mid" and "hi" are in range
    if (mid >= a.length) return;
    if (hi > a.length) hi = a.length;
    int i = lo, j = mid;
    //@ maintaining lo <= k && k <= hi;
    //@ maintaining lo <= i && i <= mid;
    //@ maintaining mid <= j && j <= hi;
    //@ maintaining k - lo == (i - lo) + (j - mid);
    //@ maintaining (\forall int t; lo <= t && t+1 < k; aux[t] <= aux[t+1]);
    //@ decreases hi - k;
    for (int k = lo; k < hi; k++) {
      if (i == mid) aux[k] = a[j++];
      else if (j == hi) aux[k] = a[i++];
      else if (a[j] < a[i]) aux[k] = a[j++];
      else aux[k] = a[i++];
    }
    // copy back
    //@ maintaining lo <= k && k <= hi;
    //@ maintaining (\forall int t; lo <= t && t < k; a[t] == aux[t]);
    //@ decreases hi - k;
    for (int k = lo; k < hi; k++) a[k] = aux[k];
  }

  /*@ spec_public @*/
  //@ requires a != null && aux != null;
  //@ requires aux.length == a.length;
  //@ requires 0 <= lo && lo <= hi && hi <= a.length;
  //@ ensures a != null && aux != null && a.length == \old(a.length) && aux.length == \old(aux.length);
  //@ ensures (\forall int t; 0 <= t && t < lo; a[t] == \old(a[t]));
  //@ ensures (\forall int t; hi <= t && t < a.length; a[t] == \old(a[t]));
  //@ ensures (\forall int i; lo <= i && i+1 < hi; a[i] <= a[i+1]);
  //@ ensures (\forall int v;
  //@            (\num_of int i; lo <= i && i < hi; a[i] == v)
  //@          == (\old(\num_of int i; lo <= i && i < hi; a[i] == v)));
  public static void recursiveMergesort(int[] a, int[] aux, int lo, int hi) {
    // base case
    if (hi - lo <= 1) return;
    // sort each half, recursively
    int mid = lo + (hi - lo) / 2;
    recursiveMergesort(a, aux, lo, mid);
    recursiveMergesort(a, aux, mid, hi);
    // merge back together
    merge(a, aux, lo, mid, hi);
  }

  /*@ spec_public @*/
  //@ requires a != null;
  //@ ensures a != null && a.length == \old(a.length);
  //@ ensures (\forall int i; 0 <= i && i+1 < a.length; a[i] <= a[i+1]);
  //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < a.length; a[i] == v) == (\old(\num_of int i; 0 <= i && i < a.length; a[i] == v)));
  public static void recursiveMergesort(int[] a) {
    int n = a.length;
    int[] aux = new int[n];
    recursiveMergesort(a, aux, 0, n);
  }
}