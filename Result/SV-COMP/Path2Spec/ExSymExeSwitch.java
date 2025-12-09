class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ requires x > 0 || System.out != null;
  //@ also
  //@ requires true;
  //@ ensures \result;
  public static boolean f(int x) {
    if (x > 0) return true;
    return test(x);
  }
  /* we want to let the user specify that this method should be symbolic */
  //@ requires true;
  //@ ensures \old(x) <= 0 ==> \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result == (\old(x) + 1 != 2);
  //@ also
  //@ requires System.out != null && x != Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ requires x == 1 && System.out != null;
  //@ ensures \result == false;
  //@ also
  //@ ensures \old(x) == 1 ==> \result == false;
  //@ also
  //@ requires x != 1;
  //@ requires java.lang.System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures \result == (\old(x) != 1);
  public static boolean test(int x) {
    x = x + 1;
    switch (x) {
      case 2:
        System.out.println("branch Foo2");
        return false;
      case 3000:
        System.out.println("branch Foo3000");
        break;
      default:
        System.out.println("default2");
        break;
    }
    return true;
  }
}
