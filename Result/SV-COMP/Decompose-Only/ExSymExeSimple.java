class Main {
  static class Node {

    //@ requires a > b;
    //@ ensures \result == false;
    //@ also
    //@ requires a == b;
    //@ ensures \result == true;
    //@ also
    //@ requires a < b;
    //@ ensures \result == true;
    public boolean test(int a, int b) {

      if (a > b) {
        return false;
      } else if (a == b) System.out.println("eq");
      else
        System.out.println("<");
      return true;
    }
  }

  //@ requires arg >= Integer.MAX_VALUE;
  //@ ensures \result == true;
  //@ also
  //@ requires arg < Integer.MAX_VALUE;
  //@ ensures \result == true;
  public static boolean f(int arg) {
    if (arg >= Integer.MAX_VALUE) return true;
    Main inst = new Main();
    Node n = new Node();
    return n.test(arg, arg + 1);
  }
}