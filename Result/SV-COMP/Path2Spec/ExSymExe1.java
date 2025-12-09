public class ExSymExe1 {
  //@ requires z == 0;
  //@ ensures \result == false;
  //@ also
  //@ requires z > 0 && z < Integer.MAX_VALUE;
  //@ requires java.lang.System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires z < 0;
  //@ ensures \result == false;
  public boolean test(int x, int z) {
    System.out.println("Testing ExSymExe1");
    x = z++;
    if (z > 0) System.out.println("branch FOO1");
    else {
      System.out.println("branch FOO2");
      return false;
    }
    if (x > 0) System.out.println("branch BOO1");
    else {
      System.out.println("branch BOO2");
      return false;
    }
    return true;
  }
}
