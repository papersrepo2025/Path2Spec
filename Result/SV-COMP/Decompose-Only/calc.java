public class calc {

  /*@ 
    @ assignable \nothing;
    @ {|
    @   requires a == null || b == null;
    @   ensures true;
    @ also
    @   requires a != null && b != null;
    @   ensures true;
    @ |}
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
    @ assignable \everything;
    @ {|
    @   requires args.length < 2;
    @   ensures true;
    @ also
    @   requires args.length >= 2;
    @   ensures true;
    @ |}
    @*/
  public static void fun(String[] args) {
    if (args.length < 2) {
      System.out.println("need two arguments");
      return;
    }
    new calc().do_stuff(args[0], args[1]);
  }
}