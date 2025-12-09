class Main {

  /*@ 
    @ public normal_behavior
    @   requires x >= 0.0f;
    @   assignable \everything;
    @   ensures \result;
    @ also
    @ public normal_behavior
    @   requires x < 0.0f;
    @   assignable \everything;
    @   ensures \result;
    @*/
  public static boolean f(float x) {
    Main inst = new Main();
    if (x >= 0)
      return inst.test(x);
    return true;
  }

  /*@
    @ public normal_behavior
    @   requires x < 0.0f;
    @   assignable \everything;
    @   ensures !\result;
    @ also
    @ public normal_behavior
    @   requires !(x < 0.0f);
    @   assignable \everything;
    @   ensures \result;
    @*/
  public boolean test(float x) {
    System.out.println("Testing FNEG");
    float y = -x;
    if (y > 0) {
      return false;
    } else
      System.out.println("branch -x <= 0");
    return true;
  }
}