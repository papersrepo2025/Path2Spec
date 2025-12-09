class Main {
  static class Node {
    //@ requires a <= b && java.lang.System.out != null;
    //@ ensures \result == true;
    //@ also
    //@ ensures \result == (a <= b);
    //@ also
    //@ ensures (a > b) ==> \result == false;
    //@ ensures (a <= b) ==> \result == true;
    public boolean test(int a, int b) {

      if (a > b) {
        return false;
      } else if (a == b) System.out.println("eq");
      else
        System.out.println("<");
      return true;
    }
  }
  //@ requires java.lang.System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean f(int arg) {
    if (arg >= Integer.MAX_VALUE) return true;
    Main inst = new Main();
    Node n = new Node();
    return n.test(arg, arg + 1);
  }
}
