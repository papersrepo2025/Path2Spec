class Main {
  /*@ spec_public @*/ static int field;
  /*@ spec_public @*/ int field2;

 
  public static boolean f(int arg) {
    if (arg == 2)
      return (new Main()).test();
    return true;
  }

  //@ ensures \result <==> (Main.field == 0 && this.field2 == 0);
  public boolean test() {
    if (field == 0 && field2 == 0) System.out.println("br 0");
    else {
      return false;
    }
    return true;
  }
}
