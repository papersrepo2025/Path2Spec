class Main {
  /*@ 
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x > 10;
    @   ensures \result == false;
    @ also
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x <= 10 && y > 10;
    @   ensures \result == false;
    @ also
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x <= 10 && y <= 10;
    @   ensures \result == true;
    @*/
  public boolean test(int x, int y) {

    int v = method_a(x, y);

    if (v > 0) {
      return false;
    }
    return true;
  }

  /*@ 
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x > 10;
    @   ensures \result == x;
    @ also
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x <= 10 && y > 10;
    @   ensures \result == y;
    @ also
    @ public normal_behavior
    @   assignable \nothing;
    @   requires x <= 10 && y <= 10;
    @   ensures \result == 0;
    @*/
  public int method_a(int x, int y) {

    if (x > 10) return x;

    if (y > 10) return y;

    return 0;
  }

  /*@ 
    @ public normal_behavior
    @   assignable \nothing;
    @   requires z > 10;
    @   ensures \result == \old(z);
    @ also
    @ public normal_behavior
    @   assignable \nothing;
    @   requires z <= 10;
    @   ensures \result == \old(z);
    @*/
  public int method_b(int z) {

    if (z > 10) return z++;
    else return z--;
  }

  /*@
    @ public normal_behavior
    @   requires arg < 0 || arg > 10;
    @   ensures \result == true;
    @ also
    @ public normal_behavior
    @   requires 0 <= arg && arg <= 10;
    @   ensures \result == true;
    @*/
  public static boolean f(int arg) {

    Main ex = new Main();
    if (arg < 0 || arg > 10) return true;
    return ex.test(arg, 0);
  }
}