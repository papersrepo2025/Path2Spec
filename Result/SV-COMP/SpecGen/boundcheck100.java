public class boundcheck100 {

  /*@ 
    @ assignable \nothing;
    @ ensures \result <==> i >= 0;
    @*/
  private static /*@ spec_public @*/ boolean recursion(int i) {
    if (i == 0) {
      return true;
    }
    if (i > 0) {
      return recursion(i - 1);
    }
    if (i < 0) {
      return false;
    }
    return true;
  }
  
}