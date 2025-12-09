class Main {

  //@ requires x > 0;
  //@ ensures \result == true;
  //@ also
  //@ requires x <= 0;
  //@ ensures \result == true;
  public static boolean main(int x) {
    x = x > 0 ? x % 10 : -(x % 10);

    Main inst = new Main();
    return inst.test(x);
  }

  //@ requires x >= 0;
  //@ ensures \result == true;
  //@ also
  //@ requires x < 0;
  //@ ensures \result == false;
  public boolean test(int x) {
    double res = (double) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}