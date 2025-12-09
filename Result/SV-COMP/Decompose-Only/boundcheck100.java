public class boundcheck100 {

  //@ requires i == 0;
  //@ ensures \result;
  //@ also
  //@ requires i > 0;
  //@ ensures \result;
  //@ also
  //@ requires i < 0;
  //@ ensures !\result;
  /*@ pure @*/ private static boolean recursion(int i) {
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