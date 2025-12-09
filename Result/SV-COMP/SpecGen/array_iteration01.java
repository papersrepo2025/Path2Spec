/*@ 
  @ // Specification-only declaration to satisfy the reference in code.
  @ public class Verifier {
  @     public static /*@ pure @*/ int nondetInt();
  @ }
  @*/

public class array_iteration01 {

  /*@
    @ // If index is within bounds, the method returns immediately with -1.
    @ ensures (0 <= index && index < 200) ==> \result == -1;
    @ // If index is out of bounds, the assignment will throw an exception.
    @ signals (ArrayIndexOutOfBoundsException e) (index < 0 || index >= 200);
    @*/
  public static int f(int index) {
    int[] ia = new int[200];
    if(0 <= index && index < ia.length) return -1;
    ia[index] = Verifier.nondetInt();
    /*@
      @ maintaining 0 <= i && i <= ia.length;
      @ maintaining (\forall int j; 0 <= j && j < i; ia[j] != 0);
      @ decreases ia.length - i;
      @*/
    for (int i = 0; i < ia.length; i++) {
      if (ia[i] == 0)
        return 0;
      ;
    }
    return 1;
  }
}