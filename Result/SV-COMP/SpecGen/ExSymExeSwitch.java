class Main {

  /*@ ensures \result == true; @*/
  public static boolean f(int x) {
    if (x > 0) return true;
    return test(x);
  }
  /* we want to let the user specify that this method should be symbolic */

  /*@ ensures \result <==> \old(x) != 1; @*/
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