class Main {
  static int field;
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    int x = 3;
    Main inst = new Main();
    field = 9;
    return inst.test2(0.0);
  }
  //@ requires true;
  //@ ensures \result == (Math.round(in) <= 16.0);
  //@ also
  //@ ensures Math.round(in) > 16.0 ==> \result == false;
  //@ ensures Math.round(in) <= 16.0 ==> \result == true;
  //@ also
  //@ requires Math.round(in) <= 16.0;
  //@ ensures \result == true;
  public boolean test2(double in) {
    if (Math.round(in) > 16.0) {
      return false;
    } else
      System.out.println("do2()");
    return true;
  }
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires x1 == 0.0 && y1 != 0.0;
  //@ requires System.out != null;
  //@ ensures true;
  //@ also
  //@ requires x1 > 0.0;
  //@ also
  //@ requires x1 == 0.0 && y1 == 0.0;
  //@ ensures x1 == 0.0 && y1 == 0.0;
  public void angleXY_(double x1, double y1) {
    double x = x1;
    double y = y1;
    if (x == 0 && y != 0) {
      System.out.println(">>>>>>>>>>>> 1");
    } else {
      System.out.println(">>>>>>>>>>>> LOOK!");
      if (x < 0) {
        System.out.println(">>>>>>>>>>>>>>> ???");
      } else if (x > 0) {
        System.out.println(">>>>>>>>>>>>>>> !!!1");
      }
    }
  }
}
