public class boundcheck30 {

  //@ requires i >= 0;
  //@ assignable \nothing;
  //@ ensures \result;
  //@ also
  //@ requires i < 0;
  //@ assignable \nothing;
  //@ ensures !\result;
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