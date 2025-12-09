class Main {
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires -99 <= arg && arg <= 99;
  public static boolean f(int arg) {
    int x = arg > 0 ? arg : -arg;
    int y = 5;
    Main inst = new Main();
    return inst.test(x, y) != x + y;
  }
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires 0 <= a && a < 100 && 0 <= b && b < 100;
  //@ ensures \result == a - b;
  //@ also
  //@ requires a < 0 || a >= 100 || b < 0 || b >= 100;
  //@ ensures \result == 0;
  public int test(int a, int b) { // invokevirtual
    int result = 0;
    if (a >= 0 && a < 100 && b >= 0 && b < 100) {
      int sum = a + b;
      int diff = a - b;
      int temp;

      if (sum > 0) temp = a;
      else temp = b;
      if (temp < diff) result = temp;
      else result = diff;
    }
    return result;
  }
}
