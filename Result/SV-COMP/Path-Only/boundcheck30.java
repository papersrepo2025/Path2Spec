public class boundcheck30 {
  //@ requires i == 0;
  //@ ensures \result == true;
  //@ also
  //@ ensures (i < 0) ==> (\result == false);
  //@ ensures (i >= 0) ==> (\result == true);
  //@ also
  //@ requires i < 0;
  //@ ensures \result == false;
  private static boolean recursion(int i) {
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
