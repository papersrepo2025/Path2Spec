public class calc {
  //@ requires a != null && b != null;
  //@ also
  //@ ensures true;
  //@ also
  //@ requires true;
  //@ also
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires a != null && b != null;
  //@ ensures true;
  void do_stuff(String a, String b) {
    try {
      int x = Integer.parseInt(a);
      int y = Integer.parseInt(b);
      assert Integer.parseInt(a) != Integer.parseInt(b) || x == y;
    } catch (java.lang.NumberFormatException e) {
    }
  }
  //@ requires args != null;
  //@ requires args.length >= 2 ==> (args[0] != null && args[1] != null);
  //@ also
  //@ requires args != null;
  //@ also
  //@ requires args == null;
  //@ also
  //@ requires args != null;
  //@ ensures true;
  //@ also
  //@ requires args != null && args.length < 2 && java.lang.System.out != null;
  //@ ensures true;
  //@ also
  //@ requires args != null && args.length >= 2 && args[0] != null && args[1] != null;
  //@ ensures true;
  public static void fun(String[] args) {
    if (args.length < 2) {
      System.out.println("need two arguments");
      return;
    }
    new calc().do_stuff(args[0], args[1]);
  }
}
