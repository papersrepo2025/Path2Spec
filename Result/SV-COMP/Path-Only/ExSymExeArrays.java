class Main {
  //@ spec_public; static int[] a;

  //@ normal_behavior;
//@ requires true;
//@ assignable \everything;
//@ ensures \result == true;
  public static boolean f() {
    a = new int[1];
    int x = -3;

    Main inst = new Main();

    return inst.test(x);
  }

  //@ normal_behavior;
//@ requires a != null && 1 <= a.length && x < 0;
//@ assignable \everything;
//@ ensures \result == true;
  public boolean test(int x) {
    a[0] = x;
    if (a[0] >= 0) {
      return false;
    } else System.out.println("branch2 <0");
    return true;
  }
}