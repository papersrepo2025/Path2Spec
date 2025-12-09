public class calc {
  /*@ 
    @ requires a != null && b != null;
    @ ensures a == \old(a) && b == \old(b);
    @*/
  void do_stuff(String a, String b) {
    try {
      int x = Integer.parseInt(a);
      int y = Integer.parseInt(b);
      assert Integer.parseInt(a) != Integer.parseInt(b) || x == y;
    } catch (java.lang.NumberFormatException e) {
    }
  }

  /*@ 
    @ requires args != null;
    @ ensures args == \old(args);
    @*/
  public static void fun(String[] args) {
    if (args.length < 2) {
      System.out.println("need two arguments");
      return;
    }
    new calc().do_stuff(args[0], args[1]);
  }
}