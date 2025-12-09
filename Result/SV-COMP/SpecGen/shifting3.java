public class shifting3 {

  /*@
    @ requires (1 <= i && i <= 100) ==> (0 <= i && i < 64);
    @ ensures (i < 1 || i > 100) ==> \result == -1;
    @ ensures (1 <= i && i <= 100) ==> (\result == (((i % 64) == 0) ? 0 : 1));
    @ ensures (1 <= i && i <= 100) ==> (\result == 0 || \result == 1);
    @ assignable \nothing;
    @*/
  public static int f(int i) {
    if (i < 1 || i > 100) {
      return -1;
    }

    return ((1L << i) != 1L) ? 1 : 0;
  }
}