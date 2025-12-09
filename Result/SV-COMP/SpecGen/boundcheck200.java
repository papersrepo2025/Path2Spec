public class boundcheck200 {

  //@ ensures \result <==> i >= 0;
  private static /*@ spec_public pure @*/ boolean recursion(int i) {
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